(ns basic-microservice-example.components.storage
  (:require
   [basic-microservice-example.protocols.storage-client :as storage-client]
   [com.stuartsierra.component :as component]))

(defrecord InMemoryStorage [storage]
  component/Lifecycle
  (start [this] this)
  (stop  [this]
    (reset! storage {})
    this)

  storage-client/StorageClient
  (read-all [_this] @storage)
  (put! [_this update-fn] (swap! storage update-fn))
  (clear-all! [_this] (reset! storage {})))

(defn new-in-memory []
  (->InMemoryStorage (atom {})))
