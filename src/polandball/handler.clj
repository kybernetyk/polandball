(ns polandball.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route])
  (:require polandball.views.all))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/all" []
      polandball.views.all/render-all) 
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
