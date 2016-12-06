(ns advent-of-code.one
  (:require [clojure.string :refer [join]]))

(defn abs [n] (max n (- n)))

(def dir-map {:north {:l :west :r :east}
              :east {:l :north :r :south}
              :south {:l :east :r :west}
              :west {:l :south :r :north}})

(def instructions
  (mapv #(let [string-name (name %)]
           [(-> (first string-name) (str) (keyword))
            (->> (rest string-name) (map str) (join) (Integer.))])
         [:r1 :l4 :l5 :l5 :r2 :r2 :l1 :l1 :r2 :l3 :r4 :r3 :r2 :l4 :l2 :r5 :l1 :r5 :l5 :l2 :l3 :l1 :r1 :r4 :r5 :l3 :r2 :l4 :l5 :r1 :r2 :l3 :r3 :l3 :l1 :l2 :r5 :r4 :r5 :l5 :r1 :l190 :l3 :l3 :r3 :r4 :r47 :l3 :r5 :r79 :r5 :r3 :r1 :l4 :l3 :l2 :r194 :l2 :r1 :l2 :l2 :r4 :l5 :l5 :r1 :r1 :l1 :l3 :l2 :r5 :l3 :l3 :r4 :r1 :r5 :l4 :r3 :r1 :l1 :l2 :r4 :r1 :l2 :r4 :r4 :l5 :r3 :l5 :l3 :r1 :r1 :l3 :l1 :l1 :l3 :l4 :l1 :l2 :r1 :l5 :l3 :r2 :l5 :l3 :r5 :r3 :l4 :l2 :r2 :r4 :r4 :l4 :r5 :l1 :l3 :r3 :r4 :r4 :l5 :r4 :r2 :l3 :r4 :r2 :r1 :r2 :l4 :l2 :r2 :l5 :l5 :l3 :r5 :l5 :l1 :r4 :l1 :r1 :l1 :r4 :l5 :l3 :r4 :r1 :l3 :r4 :r1 :l3 :l1 :r1 :r2 :l4 :l2 :r1 :l5 :l4 :l5]))

(defn move [{:keys [curr-dir pos]} [r-or-l n]]
  (let [[x y] pos
        facing (get-in dir-map [curr-dir r-or-l])]
    {:curr-dir facing
     :pos (cond
            (= facing :north) [x (+ y n)]
            (= facing :east) [(+ x n) y]
            (= facing :south) [x (- y n)]
            (= facing :west) [(- x n) y])}))

(defn compute-distance [start coords]
  (let [[x1 y1] (:pos start)
        [x2 y2] (:pos (reduce move start coords))]
    (+ (abs (- y1 y2))
       (abs (- x1 x2)))))

(compute-distance {:curr-dir :north :pos [0 0]} instructions)
