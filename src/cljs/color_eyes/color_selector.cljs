(ns color-eyes.color-selector
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [cljsjs.react :as react]
            [color-eyes.conversions :refer
             [hsl-to-rgb
              rgb-to-hsl
              css-from-rgb]]))

(defn change-color-value [color-atom color-key new-value]
  (swap! color-atom assoc color-key new-value))

(defn value-input-box [label selector current-value rgb]
  [:input {:type "number" :value current-value
           :label label
           :on-change
           #(change-color-value rgb selector
                                (js/parseInt (-> % .-target .-value)))}])

(defn color-display [css-color]
  [:div.color-display
   [:div.color-box
    {:style {:background-color css-color}}]])

(defn debug-display [rgb css hsl]
  [:div.debug-display
   [:p css]
   [:p (str hsl)]
   [:p (str (hsl-to-rgb hsl))]])

(defn selector-component [rgb]
  (let [color-map @rgb
        {:keys [r g b]} color-map
        css-color (css-from-rgb color-map)
        hsl (rgb-to-hsl color-map)]
    [:div.color-selector
     (value-input-box "Red" :r r rgb)
     (value-input-box "Green" :g g rgb)
     (value-input-box "Blue" :b b rgb)
     (color-display css-color)
     (debug-display rgb css-color hsl)]))


     



