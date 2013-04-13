(ns polandball.data.images)

(require 'polandball.data.common)
(require 'clojure.string)
(use 'korma.db)
(use 'korma.core)

(defrecord image [id image-path thumbnail-path])

(defn make-image [amap]
  (image. (:id amap)
          (:path amap)
          (:thumbnailpath amap)))

(defn get-path [image]
  (:image-path image))

(defn get-thumbnailpath [image]
  (:thumbnail-path image))

(defn get-id [image]
  (:id image))

(defn by-id [image-id]
  (make-image
   (first (exec-raw
          polandball.data.common/image-db
          ["SELECT *
           FROM images
           WHERE id = ?;" [image-id]]
          :results))))

(defn insert-new [parent-id image-path thumbnail-path]
    (:GENERATED_KEY
      (exec-raw
        polandball.data.common/image-db
        ["INSERT INTO images (parentid, path, thumbnailpath)
        VALUES (?, ?, ?);" [parent-id, image-path, thumbnail-path]])))
