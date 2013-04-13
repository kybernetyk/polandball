(ns polandball.data.common)

(use 'korma.db)
(use 'korma.core)

;;-------------- db defs -------------
(defdb ball-db
       (mysql {:db "polandball" 
               :user "root" 
               :host "localhost" 
               :password ""}))

(defdb image-db
       (mysql {:db "polandball" 
               :user "root" 
               :host "localhost" 
               :password ""}))

(defdb rating-db
       (mysql {:db "polandball" 
               :user "root" 
               :host "localhost" 
               :password ""}))


;(defdb comment-db
;	(mysql {:db "fmcomments"
;			:user "root"
;			:host "localhost"
;			:password ""}))
