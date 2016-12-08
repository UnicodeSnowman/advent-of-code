(ns advent-of-code.five
  (:require [clojure.string :refer [join]]))

(defn compute-hash [prefix suffix]
  (apply str
    (map (partial format "%02x")
      (.digest (doto (java.security.MessageDigest/getInstance "MD5")
                 .reset
                 (.update (.getBytes (str prefix suffix))))))))

(def find-pattern #(re-find #"^00000([0-9a-f])" %))

(defn the-password-is [input]
  (->> (range)
       (map #(compute-hash input %))
       (filter #(find-pattern %))
       (take 8)
       (map (comp second find-pattern))
       (join)))

(the-password-is "abbhdwsy")

