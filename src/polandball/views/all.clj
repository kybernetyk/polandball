(ns polandball.views.all
  (:require [polandball.data.balls :as balls])
  (:require [polandball.data.images :as images])
  (:require [polandball.views.common :as common]))

(defn render-all [req]
  (common/render-page "templates/all.mustache" 
                      {:balls (balls/all)
                       :ficken "nope"
                       }))
