(ns basic-microservice-example.logic-test
  (:require
   [clojure.test :refer [deftest is]]
   [basic-microservice-example.logic :as subject]))

(deftest new-account-test
  (is (= true
         (subject/foo))))
