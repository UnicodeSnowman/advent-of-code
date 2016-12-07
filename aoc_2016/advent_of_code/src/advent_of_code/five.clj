(ns advent-of-code.five)

(def input "abbhdwsy")

(def i "abc")
(def c 5017308)

(defn get-hash [prefix suffix]
  (apply str
    (map (partial format "%02x")
      (.digest (doto (java.security.MessageDigest/getInstance "MD5")
                 .reset
                 (.update (.getBytes (str prefix suffix))))))))

(get-hash i c)

(def find-pattern #(re-find #"^00000([0-9])" %))

(take 1
  (filter
    #(find-pattern %)
    (map #(get-hash i %) (range))))

(find-pattern (first (map #(get-hash i %) [3231929])))
(find-pattern "08b23036b")
(take 8 (map #(get-hash i %) (range)))
