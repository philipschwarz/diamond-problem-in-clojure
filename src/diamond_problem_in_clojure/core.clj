(ns diamond-problem-in-clojure.core)

(def alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn position-of [letter] (inc (- (int letter) (int \A))))

(defn dashes [n] (repeat n \-))

(defn core-letter-pattern-for [letter]
  (concat (dashes (dec (position-of letter))) (list letter)))

(defn core-right-quadrant-pattern-for [letter]
  (map core-letter-pattern-for (take (position-of letter) alphabet)))

(defn trailing-dashes-appender-for [max-dash-count]
  (fn [index line] (concat line (dashes (dec (- max-dash-count index))))))

(defn create-top-right-quadrant-for [letter]
  (let [number-of-letters (position-of letter)]
    (map-indexed (trailing-dashes-appender-for number-of-letters) (core-right-quadrant-pattern-for letter))))

(defn create-top-left-quadrant-from [top-right-quadrant]
  (map reverse
       (map rest top-right-quadrant)))

(defn create-top-half-of-diamond-with [top-left-quadrant top-right-quadrant]
  (map concat top-left-quadrant top-right-quadrant))

(defn create-diamond-from [top-half-of-diamond]
  (concat
    top-half-of-diamond
    (drop 1 (reverse top-half-of-diamond))))

(defn stringify [char-sequences]
  (map #(apply str %) char-sequences))

(defn print-diamond [letter]
  (let [top-right-quadrant (create-top-right-quadrant-for letter)
        top-left-quadrant (create-top-left-quadrant-from top-right-quadrant)
        top-half-of-diamond (create-top-half-of-diamond-with top-left-quadrant top-right-quadrant)
        diamond (create-diamond-from top-half-of-diamond)]
    (doseq [line (stringify diamond)]
      (println line))))

(defn -main []
  (print-diamond \E))

