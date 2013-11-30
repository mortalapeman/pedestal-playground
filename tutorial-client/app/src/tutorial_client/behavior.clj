(ns ^:shared tutorial-client.behavior
    (:require [clojure.string :as string]
              [io.pedestal.app :as app]
              [io.pedestal.app.messages :as msg]))


(defn inc-transform [old-value _]
  ((fnil inc 0) old-value))

(defn dec-transform [old-value _]
  ((fnil dec 0) old-value))

(defn init-main [_]
  [[:transform-enable [:main :my-counter] :inc [{msg/topic [:my-counter]}]]
   [:transform-enable [:main :my-counter] :dec [{msg/topic [:my-counter]}]]])

(def example-app
  {:version 2
   :transform [[:inc [:my-counter] inc-transform]
               [:dec [:my-counter] dec-transform]]
   :emit [{:init init-main}
          [#{[:*]} (app/default-emitter [:main])]]})

