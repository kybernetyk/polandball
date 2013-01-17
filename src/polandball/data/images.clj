(ns polandball.data.images)

(require 'polandball.data.common)
(use 'korma.db)
(use 'korma.core)

(defn by-parent-url [parent-url]
  (first (exec-raw
           polandball.data.common/image-db
           ["SELECT * 
            FROM images 
            WHERE parenturl = ?;" [parent-url]]
           :results)))

(defn insert-new [image]
  (exec-raw
    polandball.data.common/image-db
    ["INSERT INTO images (parenturl, url, path, thumbnailurl, thumbnailpath)
     VALUES (?, ?, ?, ?, ?);" [(:parenturl image) (:url image) (:path image) (:thumbnailurl image) (:thumbnailpath image)]]))

