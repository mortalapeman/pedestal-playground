(ns tutorial-client.rendering
  (:require [domina :as dom]
            [io.pedestal.app.render.push :as render]
            [io.pedestal.app.render.events :as events]
            [io.pedestal.app.render.push.templates :as templates]
            [io.pedestal.app.render.push.handlers :as h]
            [io.pedestal.app.render.push.handlers.automatic :as d])
  (:require-macros [tutorial-client.html-templates :as html-templates]))

(def templates (html-templates/tutorial-client-templates))

(defn render-template [renderer [_ path] _]
  (let [parent (render/get-parent-id renderer path)
        id (render/new-id! renderer path)
        html (templates/add-template renderer path (:tutorial-client-page templates))]
    (dom/append! (dom/by-id parent) (html {:id id}))))

(defn render-value [renderer [_ path _ new-value] input-queue]
  (let [key (last path)]
    (templates/update-t renderer [:main] {key (str new-value)})))

(defn enable-buttons [_ [_ path transform-name messages] input-queue]
  (condp = transform-name
    :inc (events/send-on :click "inc-button" input-queue transform-name messages)
    :dec (events/send-on :click "dec-button" input-queue transform-name messages)))

(defn render-config []
  [[:node-create  [:main] render-template]
   [:node-destroy   [:main] d/default-destroy]
   [:value [:main :*] render-value]
   [:transform-enable [:main :my-counter] enable-buttons]])
