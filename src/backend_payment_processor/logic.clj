(ns basic-microservice-example.logic)

(defn new-account [customer-id customer-name]
  {:id          (random-uuid)
   :name        customer-name
   :customer-id customer-id})
