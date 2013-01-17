(ns polandball.data.balls)

(require 'polandball.data.common)
(use 'korma.db)
(use 'korma.core)

(defn by-url [url]
  (first (exec-raw
           polandball.data.common/ball-db
           ["SELECT b.id, b.url, m.title, m.description, m.timestamp, m.public 
            FROM balls b, metadata m
            WHERE b.url = m.parenturl AND b.url = ?;" [url]]
           :results)))

(defn total-count []
    (:cnt (first (exec-raw 
             polandball.data.common/ball-db 
             ["SELECT COUNT(*) AS cnt FROM balls;"] 
             :results))))

(defn random []
  (let [url (:url (first (exec-raw
                     polandball.data.common/ball-db
                     ["SELECT url FROM balls ORDER BY RAND() LIMIT 1;"]
                     :results)))]
    (by-url url)))

(defn insert-new [ball-url ball-title ball-description]
  (exec-raw
    polandball.data.common/ball-db
    ["INSERT INTO balls (url)
     VALUES (?)" [ball-url]])
  (exec-raw
    polandball.data.common/ball-db
    ["INSERT INTO metadata (parenturl, title, description)
     VALUES (?, ?, ?)" [ball-url ball-title ball-description]]))



