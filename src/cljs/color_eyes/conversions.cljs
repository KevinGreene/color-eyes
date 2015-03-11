(ns color-eyes.conversions)

(defn abs "(abs n) is the absolute value of n" [n]
  (cond
    (not (number? n)) 0
    (neg? n) (- n)
    :else n))

(defn css-from-rgb [color-map]
  (let [{:keys [r g b]} color-map]
    (str "rgb(" r "," g "," b ")")))

(defn hsl-to-rgb [hsl-map]
  (let [{:keys [h s l]} hsl-map
        c (* s (- 1 (abs (- (* 2 l) 1))))
        x (* c (- 1 (abs (- (mod (/ h 60) 2) 1))))
        m (- l (/ c 2))
        c' (* 255 (+ c m))
        x' (* 255 (+ x m))
        m' (* 255 m)]
    (cond
      (and (<= 0 h) (< h 60)) {:r c' :g x' :b m'}
      (and (<= 60 h) (< h 120)) {:r x' :g c' :b m'}
      (and (<= 120 h) (< h 180)) {:r m' :g c' :b x'}
      (and (<= 180 h) (< h 240)) {:r m' :g x' :b c'}
      (and (<= 240 h) (< h 300)) {:r x' :g m' :b c'}
      (and (<= 300 h) (< h 360)) {:r c' :g m' :b x'})))

(defn calculate-h-from-primes [r' g' b']
  (let [c-max (max r' g' b')
        c-min (min r' g' b')
        delta (- c-max c-min)]
    (cond
      (= c-max c-min) 0
      (= c-max r') (* 60 (mod (/ (- g' b') delta) 6))
      (= c-max g') (* 60 (+ (/ (- b' r') delta) 2))
      (= c-max b') (* 60 (+ (/ (- r' g') delta) 4)))
    ))

(defn calculate-s-from-primes-and-l [r' g' b' l]
  (let [c-max (max r' g' b')
        c-min (min r' g' b')
        delta (- c-max c-min)]
    (if (= 0 delta) 0
                    (/ delta (- 1 (abs (- (* 2 l) 1)))))))

(defn calculate-l-from-primes [r' g' b']
  (let [c-max (max r' g' b')
        c-min (min r' g' b')]
    (/ (+ c-max c-min) 2)))

(defn rgb-to-hsl [rgb-map]
  (let [{:keys [r g b]} rgb-map
        r' (/ r 255)
        g' (/ g 255)
        b' (/ b 255)
        h (calculate-h-from-primes r' g' b')
        l (calculate-l-from-primes r' g' b')
        s (calculate-s-from-primes-and-l r' g' b' l)]
    {:h h :s s :l l}))