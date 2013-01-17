(ns polandball.data.common)

(use 'korma.db)
(use 'korma.core)

;;-------------- db defs -------------
(defdb ball-db
       (mysql {:db "pb_balls" 
               :user "root" 
               :host "localhost" 
               :password ""}))

(defdb image-db
       (mysql {:db "pb_images" 
               :user "root" 
               :host "localhost" 
               :password ""}))

(defdb rating-db
       (mysql {:db "pb_ratings" 
               :user "root" 
               :host "localhost" 
               :password ""}))


;(defdb comment-db
;	(mysql {:db "fmcomments"
;			:user "root"
;			:host "localhost"
;			:password ""}))
