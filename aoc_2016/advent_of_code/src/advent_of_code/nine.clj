(ns advent-of-code.nine)

(def compressed-data (slurp
                       (str
                          (-> (ClassLoader/getSystemResource *file*) clojure.java.io/file .getParent)
                          "/nine.txt")))

(defn get-marker [data]
  (let [match (re-find #"^\(([0-9]+)x([0-9]+)\)" data)]
    (if match
      [(nth match 1) (nth match 2)])))

(defn compressed-length 
  ([data] (compressed-length 0 data))
  ([cnt data]
     (if (seq data)
       (let [[num-chars times] (get-marker data)]
              (if (and num-chars times)
                (recur (+ cnt (* (Integer. num-chars) (Integer. times)))
                       (subs data (+
                                (count (str "(" num-chars "x" times ")"))
                                (Integer. num-chars))))
                (recur (inc cnt)
                       (subs data 1))))
       cnt)))

; ADVENT   : 6
; A(1x5)BC : 7
; (3x3)XYZ : 9
; A(2x2)BCD(2x2)EFG : 11
; (6x1)(1x3)A : 6
; X(8x2)(3x3)ABCY : 18

(compressed-length (clojure.string/trim-newline compressed-data))
