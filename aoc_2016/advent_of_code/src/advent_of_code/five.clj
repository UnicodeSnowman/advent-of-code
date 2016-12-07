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

(def find-pattern #(re-find (re-pattern (str "^" 00000 "([0-9])")) %))

(take 8
  (filter
    #(find-pattern %)
    (map #(get-hash i %) (range))))

(find-pattern (first (map #(get-hash i %) [5017308])))
