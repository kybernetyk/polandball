(ns polandball.data.images)

(require 'polandball.data.common)
(require 'clojure.string)
(use 'korma.db)
(use 'korma.core)

;; note: image urls are fetched in balls/by-url already
;; no need to do this if you just want to get a ball


(defn by-parent-url [parent-url]
  (first (exec-raw
           polandball.data.common/image-db
           ["SELECT * 
            FROM images 
            WHERE parenturl = ?;" [parent-url]]
           :results)))

(defn image-url-from-path [path]
  (str "http://localhost/media/images/" 
       (last 
         (clojure.string/split path #"/"))))

(defn thumb-url-from-path [path]
  (str "http://localhost/media/thumbnails/"
       (last
         (clojure.string/split path #"/"))))

(defn insert-new [parent-url image-path thumbnail-path]
  (let [image-url (image-url-from-path image-path)
        thumb-url (thumb-url-from-path thumbnail-path)] 
    (exec-raw
      polandball.data.common/image-db
      ["INSERT INTO images (parenturl, url, path, thumbnailurl, thumbnailpath)
       VALUES (?, ?, ?, ?, ?);" [parent-url, image-url, image-path, thumb-url, thumbnail-path]])
    image-url))

