(defproject polandball "0.1.0-SNAPSHOT"
  :description "The biggest polandball resource in the space"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.5" :exclusions [org.clojure/clojure]]
                 [korma "0.3.0-beta15" :exclusions [org.clojure/clojure]]
                 [mysql/mysql-connector-java "5.1.6" :exclusions [org.clojure/clojure]]
                 [de.ubercode.clostache/clostache "1.3.1" :exclusions [org.clojure/clojure]]
                 [clj-time "0.4.4" :exclusions [org.clojure/clojure]]
                 [org.jsoup/jsoup "1.6.2"]]
  :plugins [[lein-ring "0.8.0"]]
  :ring {:handler polandball.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
