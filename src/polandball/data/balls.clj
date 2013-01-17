(ns polandball.data.balls)

(require 'polandball.data.common)
(use 'korma.db)
(use 'korma.core)

(defn by-url [url]
  (first (exec-raw
           polandball.data.common/ball-db
           ["SELECT b.id, b.url, m.title, m.description, m.timestamp, i.path as imagepath, i.url as imageurl 
            FROM balls b, metadata m, pb_images.images i
            WHERE b.url = m.parenturl AND b.url = i.parenturl AND b.url = ?;" [url]]
           :results)))

(defn total-count []
    (first (exec-raw 
             polandball.data.common/ball-db 
             ["SELECT COUNT(*) FROM balls;"] 
             :results)))

(defn random []
  (let [url (:url (first (exec-raw
                     polandball.data.common/ball-db
                     ["SELECT url FROM balls ORDER BY RAND() LIMIT 1;"]
                     :results)))]
    (by-url url)))
