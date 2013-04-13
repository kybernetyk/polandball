(ns polandball.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route])
  (:require polandball.views.all)
  (:require polandball.views.pic))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/all" req
      (polandball.views.all/render-all req))
  (GET "/ball/:id" [id]
  	(polandball.views.all/render-ball-by-id id))
  (GET "/view/image/:id" [id]
       (polandball.views.pic/render-image-by-id id))
  (GET "/view/thumb/:id" [id]
       (polandball.views.pic/render-thumbnail-by-id id))

  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
