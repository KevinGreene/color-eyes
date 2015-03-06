(ns color-eyes.color-selector
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [cljsjs.react :as react]
            [color-eyes.util :as color-util]))

(defn change-color-value [color-atom color-key new-value]
  (swap! color-atom assoc color-key new-value))

(defn selector-component [rgb]
  (let [color-map @rgb
        {:keys [r g b]} color-map
        css-color (color-util/css-from-color-map color-map)
        hsl-color (color-util/rgb-to-hsl color-map)]
    [:div.color-selector
     [:input {:type "number" :value r
              :on-change
              #(change-color-value rgb :r (js/parseInt (-> % .-target .-value)))}]
     [:input {:type "number" :value g
              :on-change
              #(change-color-value rgb :g (js/parseInt (-> % .-target .-value)))}]
     [:input {:type "number" :value b
              :on-change
              #(change-color-value rgb :b (js/parseInt (-> % .-target .-value)))}]
     [:div.color-display
      [:div.color-box
       {:style {:background-color css-color}}]]
     [:p css-color]
     [:p (str hsl-color)]
     [:p (str (color-util/hsl-to-rgb hsl-color))]])
  )


