(ns basic-microservice-example.aux.http-client-helpers
  (:require
   [basic-microservice-example.components.mock-http :as mock-http]
   [state-flow.api :refer [flow get-state return]]))

(defn add-responses
  [mock-response]
  (flow "mock http client"
    [http-client (get-state (comp :http :system))]
    (return (mock-http/add-responses http-client mock-response))))
