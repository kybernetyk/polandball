(ns polandball.data.balls)

(require 'polandball.data.common)
(use 'korma.db)
(use 'korma.core)

(defrecord ball [id title description timestamp public image-id])

(defn make-ball [amap]
  (ball. (:id amap)
         (:title amap)
         (:description amap)
         (:timestamp amap)
         (:public amap)
         (:imageid amap)))

(defn by-id [id]
  (make-ball
    (first (exec-raw
           polandball.data.common/ball-db
           ["SELECT b.id, m.title, m.description, m.timestamp, m.public, i.id as imageid
            FROM balls b, metadata m, images i
            WHERE b.id = m.parentid AND b.id = i.parentid AND b.id = ?;" [id]]
           :results))))


(defn all []
  (map make-ball
  (exec-raw
    polandball.data.common/ball-db
    ["SELECT b.id, m.title, m.description, m.timestamp, m.public, i.id as imageid
      FROM balls b, metadata m, images i
      WHERE b.id = m.parentid AND b.id = i.parentid;"]
    :results)))

(defn total-count []
    (:cnt (first (exec-raw
             polandball.data.common/ball-db
             ["SELECT COUNT(*) AS cnt FROM balls;"]
             :results))))

(defn random []
  (let [id (:id (first
                    (exec-raw
                     polandball.data.common/ball-db
                     ["SELECT id FROM balls ORDER BY RAND() LIMIT 1;"]
                     :results)))]
    (by-id id)))

(defn newest-ball-id []
  (:ballid
    (first
      (exec-raw polandball.data.common/ball-db
                ["SELECT LAST_INSERT_ID() as ballid;"] :results))))

(defn insert-new [ball-title ball-description]
  (println "inserting new ball:" ball-title ball-description)
  (exec-raw polandball.data.common/ball-db ["INSERT INTO BALLS () VALUES ();"])
  (let [ball-id (newest-ball-id)]
      (println "ball id is:" ball-id)
      (exec-raw
        polandball.data.common/ball-db
        ["INSERT INTO metadata (parentid, title, description)
        VALUES (?, ?, ?)" [ball-id ball-title ball-description]])
    ball-id))
