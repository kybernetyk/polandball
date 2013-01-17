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

(defn insert-new [parent-url image-path thumbnail-path]
  (let [image-url (str "http://xd2000.de/media/image")
        thumb-url (str "http://xd2000.de/media/thumb")]
    (exec-raw
      polandball.data.common/image-db
      ["INSERT INTO images (parenturl, url, path, thumbnailurl, thumbnailpath)
       VALUES (?, ?, ?, ?, ?);" [parent-url, image-url, image-path, thumb-url, thumbnail-path]])
    image-url))

