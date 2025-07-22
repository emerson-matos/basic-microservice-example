(ns basic-microservice-example.controller-test
  (:require
   [basic-microservice-example.controller :as controller]
   [basic-microservice-example.db.saving-account :as db.saving-account]
   )
  )

(def customer-id (random-uuid))

#_(fact "Sketching account creation"
      (controller/create-account! customer-id ..storage.. ..http..) => (just {:id          uuid?
                                                                              :name        "Abel"
                                                                              :customer-id customer-id})
      (provided
       (controller/get-customer customer-id ..http..) => {:customer-name "Abel"}
       (db.saving-account/add-account! (contains {:name "Abel"}) ..storage..) => irrelevant))
