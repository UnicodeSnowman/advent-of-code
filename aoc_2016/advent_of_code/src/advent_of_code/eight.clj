(ns advent-of-code.eight
  (:require [clojure.string :refer [split]]))

(def instructions [
  "rect 1x1"
  "rotate row y=0 by 6"
  "rect 1x1"
  "rotate row y=0 by 3"
  "rect 1x1"
  "rotate row y=0 by 5"
  "rect 1x1"
  "rotate row y=0 by 4"
  "rect 2x1"
  "rotate row y=0 by 5"
  "rect 2x1"
  "rotate row y=0 by 2"
  "rect 1x1"
  "rotate row y=0 by 5"
  "rect 4x1"
  "rotate row y=0 by 2"
  "rect 1x1"
  "rotate row y=0 by 3"
  "rect 1x1"
  "rotate row y=0 by 3"
  "rect 1x1"
  "rotate row y=0 by 2"
  "rect 1x1"
  "rotate row y=0 by 6"
  "rect 4x1"
  "rotate row y=0 by 4"
  "rotate column x=0 by 1"
  "rect 3x1"
  "rotate row y=0 by 6"
  "rotate column x=0 by 1"
  "rect 4x1"
  "rotate column x=10 by 1"
  "rotate row y=2 by 16"
  "rotate row y=0 by 8"
  "rotate column x=5 by 1"
  "rotate column x=0 by 1"
  "rect 7x1"
  "rotate column x=37 by 1"
  "rotate column x=21 by 2"
  "rotate column x=15 by 1"
  "rotate column x=11 by 2"
  "rotate row y=2 by 39"
  "rotate row y=0 by 36"
  "rotate column x=33 by 2"
  "rotate column x=32 by 1"
  "rotate column x=28 by 2"
  "rotate column x=27 by 1"
  "rotate column x=25 by 1"
  "rotate column x=22 by 1"
  "rotate column x=21 by 2"
  "rotate column x=20 by 3"
  "rotate column x=18 by 1"
  "rotate column x=15 by 2"
  "rotate column x=12 by 1"
  "rotate column x=10 by 1"
  "rotate column x=6 by 2"
  "rotate column x=5 by 1"
  "rotate column x=2 by 1"
  "rotate column x=0 by 1"
  "rect 35x1"
  "rotate column x=45 by 1"
  "rotate row y=1 by 28"
  "rotate column x=38 by 2"
  "rotate column x=33 by 1"
  "rotate column x=28 by 1"
  "rotate column x=23 by 1"
  "rotate column x=18 by 1"
  "rotate column x=13 by 2"
  "rotate column x=8 by 1"
  "rotate column x=3 by 1"
  "rotate row y=3 by 2"
  "rotate row y=2 by 2"
  "rotate row y=1 by 5"
  "rotate row y=0 by 1"
  "rect 1x5"
  "rotate column x=43 by 1"
  "rotate column x=31 by 1"
  "rotate row y=4 by 35"
  "rotate row y=3 by 20"
  "rotate row y=1 by 27"
  "rotate row y=0 by 20"
  "rotate column x=17 by 1"
  "rotate column x=15 by 1"
  "rotate column x=12 by 1"
  "rotate column x=11 by 2"
  "rotate column x=10 by 1"
  "rotate column x=8 by 1"
  "rotate column x=7 by 1"
  "rotate column x=5 by 1"
  "rotate column x=3 by 2"
  "rotate column x=2 by 1"
  "rotate column x=0 by 1"
  "rect 19x1"
  "rotate column x=20 by 3"
  "rotate column x=14 by 1"
  "rotate column x=9 by 1"
  "rotate row y=4 by 15"
  "rotate row y=3 by 13"
  "rotate row y=2 by 15"
  "rotate row y=1 by 18"
  "rotate row y=0 by 15"
  "rotate column x=13 by 1"
  "rotate column x=12 by 1"
  "rotate column x=11 by 3"
  "rotate column x=10 by 1"
  "rotate column x=8 by 1"
  "rotate column x=7 by 1"
  "rotate column x=6 by 1"
  "rotate column x=5 by 1"
  "rotate column x=3 by 2"
  "rotate column x=2 by 1"
  "rotate column x=1 by 1"
  "rotate column x=0 by 1"
  "rect 14x1"
  "rotate row y=3 by 47"
  "rotate column x=19 by 3"
  "rotate column x=9 by 3"
  "rotate column x=4 by 3"
  "rotate row y=5 by 5"
  "rotate row y=4 by 5"
  "rotate row y=3 by 8"
  "rotate row y=1 by 5"
  "rotate column x=3 by 2"
  "rotate column x=2 by 3"
  "rotate column x=1 by 2"
  "rotate column x=0 by 2"
  "rect 4x2"
  "rotate column x=35 by 5"
  "rotate column x=20 by 3"
  "rotate column x=10 by 5"
  "rotate column x=3 by 2"
  "rotate row y=5 by 20"
  "rotate row y=3 by 30"
  "rotate row y=2 by 45"
  "rotate row y=1 by 30"
  "rotate column x=48 by 5"
  "rotate column x=47 by 5"
  "rotate column x=46 by 3"
  "rotate column x=45 by 4"
  "rotate column x=43 by 5"
  "rotate column x=42 by 5"
  "rotate column x=41 by 5"
  "rotate column x=38 by 1"
  "rotate column x=37 by 5"
  "rotate column x=36 by 5"
  "rotate column x=35 by 1"
  "rotate column x=33 by 1"
  "rotate column x=32 by 5"
  "rotate column x=31 by 5"
  "rotate column x=28 by 5"
  "rotate column x=27 by 5"
  "rotate column x=26 by 5"
  "rotate column x=17 by 5"
  "rotate column x=16 by 5"
  "rotate column x=15 by 4"
  "rotate column x=13 by 1"
  "rotate column x=12 by 5"
  "rotate column x=11 by 5"
  "rotate column x=10 by 1"
  "rotate column x=8 by 1"
  "rotate column x=2 by 5"
  "rotate column x=1 by 5"])

(def dimensions {:y 50 :x 6})

(def screen (->> "." (repeat (:y dimensions)) vec (repeat (:x dimensions)) vec))

(def build-rect-coords #(for [x (range (Integer. %1)) y (range (Integer. %2))] {:x x :y y :val "#"}))

(defn update-screen [screen {:keys [x y val]}]
  (assoc-in screen [y x] val))

(defmulti action (fn [acc c] (first (split c #" "))))
(defmethod action "rect" [acc c]
  (let [[x y] (-> c
                  (split #" ")
                  (second)
                  (split #"x"))]
    (reduce update-screen acc (build-rect-coords x y))))

(defmethod action "rotate" [acc c]
  (let [[_ x-or-y n shift] (re-find #"rotate \w+ ([x|y])=([0-9]+) by ([0-9]+)" c)
        num-items ((keyword x-or-y) dimensions)
        coords (map (fn [[x y]]
                      {:x x :y y :val (get-in acc [y x])})
                    (range num-items)
                    (repeatedly (constantly n)))]
    (reduce update-screen acc (shift x-or-y n coords)))
  acc)

; shift y: [y 0] [y 1] [y 2] ... 50 items
; shift x: [0 x] [1 x] [2 x] ... 6 items
;"rotate row y=0 by 6"

(defn shift [x-or-y n coords]
  (map (fn [{:keys [x y val]}]
         {:val val
          :y y
          :x (nth
               (cycle (range (Integer. (x-or-y dimensions))))
               (+ n x))})
       coords))

(reduce action screen instructions)
