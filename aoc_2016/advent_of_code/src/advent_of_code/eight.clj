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
  "rotate column x=1 by 5"
])

(def dimensions {:x 50 :y 6})
(def screen (->> "." (repeat (:x dimensions)) vec (repeat (:y dimensions)) vec))
(def build-rect-coords #(for [x (range (Integer. %1)) y (range (Integer. %2))] {:x x :y y :val "#"}))

(defn update-screen [screen {:keys [x y val]}]
  (assoc-in screen [y x] val))

; after shifting past end of row, start back at beginning...
(defn shift [v amt row-length]
  (nth
    (cycle (range row-length))
    (+ v amt)))

(defn shift-x [num-values shift-amt coords]
  (map (fn [{:keys [x y val]}]
         {:val val
          :x (shift x shift-amt num-values)
          :y y})
       coords))

(defn shift-y [num-values shift-amt coords]
  (map (fn [{:keys [x y val]}]
         {:val val
          :x x
          :y (shift y shift-amt num-values)})
       coords))

(defmulti action (fn [acc c]
                   (let [parts (split c #" ")]
                     (cond
                       (= (first parts) "rect") :rect
                       (= (take 2 parts) ["rotate" "row"]) :rotate_row
                       (= (take 2 parts) ["rotate" "column"]) :rotate_column))))

(defmethod action :rect [acc c]
  (let [[x y] (-> c
                  (split #" ")
                  (second)
                  (split #"x"))]
    (reduce update-screen acc (build-rect-coords x y))))

; TODO clean/DRY up these two `action` definitions, this is super ugly/repetitive
(defmethod action :rotate_row [acc c]
  (let [[_ _ n shift-amt] (re-find #"rotate \w+ ([x|y])=([0-9]+) by ([0-9]+)" c)
        num-items (:x dimensions)
        coords (map (fn [x y]
                      {:x x :y y :val (get-in acc [y x])})
                    (range num-items)
                    (repeatedly (constantly (Integer. n))))]
    (reduce update-screen
            acc
            (shift-x num-items (Integer. shift-amt) coords))))

(defmethod action :rotate_column [acc c]
  (let [[_ _ n shift-amt] (re-find #"rotate \w+ ([x|y])=([0-9]+) by ([0-9]+)" c)
        num-items (:y dimensions)
        coords (map (fn [x y]
                      {:x x :y y :val (get-in acc [y x])})
                    (repeatedly (constantly (Integer. n)))
                    (range num-items))]
    (reduce update-screen
            acc
            (shift-y num-items (Integer. shift-amt) coords))))

(defn show-screen [instructions]
  (reduce action screen instructions))

(show-screen instructions)

(defn num-pixels [screen]
  (count
    (filter #(= %1 "#") (flatten screen))))

(num-pixels (show-screen instructions))

; print screen
; (doseq [row (reduce action screen instructions)]
;   (prn row))
