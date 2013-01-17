(ns polandball.data.slave
  (:use [clojure.java.io :only (file copy as-file)])
  (:require [clojure.string :as string])
  (:require [polandball.data.images :as images])
  (:require [polandball.data.balls :as balls]))

(import javax.imageio.ImageIO) 
(import java.awt.image.BufferedImage)

(defn make-thumbnail [filename new-filename width]
  (println (str "Generating thumbnail: " new-filename))
  (let [img (javax.imageio.ImageIO/read (as-file filename))
        imgtype (java.awt.image.BufferedImage/TYPE_INT_ARGB)
        width (min (.getWidth img) width)
        height (* (/ width (.getWidth img)) (.getHeight img))
        simg (java.awt.image.BufferedImage. width height imgtype)
        g (.createGraphics simg)]
    (.drawImage g img 0 0 width height nil)
    (.dispose g)
    (javax.imageio.ImageIO/write simg "png" (as-file new-filename))))

(defn copy-file [src-path dest-path]
  (copy (file src-path) (file dest-path)))

(def source-dir (file "/var/www/polandball/upload"))
(def image-dir (file "/var/www/polandball/media/images"))
(def thumb-dir (file "/var/www/polandball/media/thumbnails"))

(defn migrate-file [file]
  (let [source-path (.getPath file)
        file-name (string/lower-case (.getName file))
        dest-path (str image-dir "/" file-name)
        thumb-path (str thumb-dir "/" file-name ".png")]
    (copy-file source-path dest-path)
    (make-thumbnail source-path thumb-path 320)
    {:path dest-path
     :thumbnailpath thumb-path}))

(defn migrate-files [files]
  (vec (map migrate-file files)))

(defn string-contains? [^String big ^String little]
      (not (neg? (.indexOf big little))))

(def allowed-list [".jpg" ".png"])

(defn is-valid-file? [file]
  (let [file-name (string/lower-case (.getName file))]
   (not= 0 (count (filter (fn [ext] (string-contains? file-name ext)) allowed-list)))))


(defn insert-to-database [image]
  (let [ball-url (balls/insert-new "new polandball desc" "new polandball title")]
    (println (str "new ball url: " ball-url))
    (images/insert-new ball-url (:path image) (:thumbnailpath image))))

(defn push-to-database [images]
 (map insert-to-database images)) 

(defn migrate-new []
  (let [files (rest (file-seq source-dir))
        files (filter is-valid-file? files)
        migrated-files (migrate-files files)]
    (push-to-database migrated-files)))

