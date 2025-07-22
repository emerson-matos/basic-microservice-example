(ns basic-microservice-example.logic-test
  (:require
   [clojure.test :refer [deftest is]]
   [matcher-combinators.test :refer [match?]]
   [basic-microservice-example.logic :as subject]))

(deftest new-account-test
  (is (match? {:customer-id {},
               :id uuid?,
               :name {}}
         (subject/new-account {} {}))))
