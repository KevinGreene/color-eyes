(ns color-eyes.util
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [cljsjs.react :as react]))

(defn css-from-color-map [color-map]
  (let [{:keys [r g b a]} color-map]
    (str "rgba(" r "," g "," b "," a ");")))
