(ns color-eyes.compliments)

(defn get-monochromatic-colors [hsl-map]
  (let [{:keys [h s l]} hsl-map
        half-saturation (* s 0.5)]
    [hsl-map
     {:h h :s half-saturation :l l}])
  )