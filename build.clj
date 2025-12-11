(ns build
  (:require [clojure.tools.build.api :as b]))

(def lib 'basic-microservice-example/basic-microservice-example)
(def version "0.0.1-SNAPSHOT")
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def uber-file (format "target/%s-%s-standalone.jar" (name lib) version))

(defn clean [_]
  (b/delete {:path "target"}))

(defn uber [_]
  (clean nil)
  (b/copy-dir {:src-dirs ["src" "resources" "config"]
               :target-dir class-dir})
  (b/compile-clj {:basis basis
                  :ns-compile '[basic-microservice-example.server]
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis basis
           :main 'basic-microservice-example.server})
  (println "Uberjar created:" uber-file))
