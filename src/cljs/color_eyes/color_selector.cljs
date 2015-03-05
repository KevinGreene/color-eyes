(ns color-eyes.color-selector
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [cljsjs.react :as react]
            [color-eyes.util :as color-util]))

(defn change-color-value [color-atom color-key new-value]
  (swap! color-atom assoc color-key new-value))

(defn selector-component [rgba]
  (let [color-map @rgba
        {:keys [r g b a]} color-map
        css-color (color-util/css-from-color-map color-map)]
    [:div.color-selector
     [:input {:type "number" :value r
              :on-change
              #(change-color-value rgba :r (js/parseInt (-> % .-target .-value)))}]
     [:input {:type "number" :value g
              :on-change
              #(change-color-value rgba :g (js/parseInt (-> % .-target .-value)))}]
     [:input {:type "number" :value b
              :on-change
              #(change-color-value rgba :b (js/parseInt (-> % .-target .-value)))}]
     [:input {:type "number" :value a
              :on-change
              #(change-color-value rgba :a (js/parseInt (-> % .-target .-value)))}]
     [:div.color-display
      [:div.color-box
       {:style {:background-color css-color}}]]
     [:p (str css-color)]])
  )

