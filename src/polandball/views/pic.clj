(ns polandball.views.pic
  (:require [polandball.data.images :as images])
  (:require [polandball.views.common :as common])
  (:import  (java.io FileInputStream FileNotFoundException IOException File)))

(defn render-image-by-path [image-path]
  {:status 200
   :headers {"Content-Type" "image/jpeg"}
   :body (FileInputStream. image-path)})

(defn render-image-by-id [image-id]
  (let [i (images/by-id image-id)]
    (render-image-by-path (images/get-path i))))

(defn render-thumbnail-by-id [thumb-id]
  (let [i (images/by-id thumb-id)]
    (render-image-by-path (images/get-thumbnailpath i) )))
