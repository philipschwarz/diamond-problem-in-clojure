(ns diamond-problem-in-clojure.core-test
  (:require [clojure.test :refer :all]
            [diamond-problem-in-clojure.core :refer :all]))

(deftest position-of-A
  (is (= 1 (position-of \A))))

(deftest position-of-Z
  (is (= 26 (position-of \Z))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest no-dashes
  (is (= () (dashes 0))))

(deftest one-dash
  (is (= (seq "-") (dashes 1))))

(deftest five-dashes
  (is (= (seq "-----") (dashes 5))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest core-letter-pattern-for-A
  (is (= (seq "A") (core-letter-pattern-for \A))))

(deftest core-letter-pattern-for-B
  (is (= (seq "-B") (core-letter-pattern-for \B))))

(deftest core-letter-pattern-for-F
  (is (= (seq "-----F") (core-letter-pattern-for \F))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest core-right-quadrant-pattern-for-A
  (is (= (list (seq "A")) (core-right-quadrant-pattern-for \A))))

(deftest core-right-quadrant-pattern-for-B
  (is (= (list (seq "A")
               (seq "-B")) (core-right-quadrant-pattern-for \B))))

(deftest core-right-quadrant-pattern-for-F
  (is (=
         (list (seq "A")
               (seq "-B")
               (seq "--C")
               (seq "---D")
               (seq "----E")
               (seq "-----F"))
        (core-right-quadrant-pattern-for \F))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest appending-trailing-dashes
  (let [number-of-letters (position-of \F)]
    (is (= (list (seq "A-----")
                 (seq "-B----")
                 (seq "--C---")
                 (seq "---D--")
                 (seq "----E-")
                 (seq "-----F")
            (map-indexed
              trailing-dashes-appender-for
              (list (seq "A")
                    (seq "-B")
                    (seq "--C")
                    (seq "---D")
                    (seq "----E")
                    (seq "-----F"))))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest creating-top-right-quadrant
  (is (= (list (seq "A-----")
               (seq "-B----")
               (seq "--C---")
               (seq "---D--")
               (seq "----E-")
               (seq "-----F"))
          (create-top-right-quadrant-for \F))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest creating-top-left-quadrant-from-top-right-quadrant
  (is (= (list (seq "-----")
               (seq "----B")
               (seq "---C-")
               (seq "--D--")
               (seq "-E---")
               (seq "F----"))
        (create-top-left-quadrant-from (list (seq "A-----")
                                             (seq "-B----")
                                             (seq "--C---")
                                             (seq "---D--")
                                             (seq "----E-")
                                             (seq "-----F"))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest creating-top-half-of-diamond-with-top-left-quadrant-and-top-right-quadrant
  (is (= (list (seq "-----A-----")
               (seq "----B-B----")
               (seq "---C---C---")
               (seq "--D-----D--")
               (seq "-E-------E-")
               (seq "F---------F"))
          (create-top-half-of-diamond-with
            (list (seq "-----")
                  (seq "----B")
                  (seq "---C-")
                  (seq "--D--")
                  (seq "-E---")
                  (seq "F----"))
            (list (seq "A-----")
                  (seq "-B----")
                  (seq "--C---")
                  (seq "---D--")
                  (seq "----E-")
                  (seq "-----F"))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest creating-diamond-from-top-half-of-diamond
  (is (= (list (seq "-----A-----")
               (seq "----B-B----")
               (seq "---C---C---")
               (seq "--D-----D--")
               (seq "-E-------E-")
               (seq "F---------F")
               (seq "-E-------E-")
               (seq "--D-----D--")
               (seq "---C---C---")
               (seq "----B-B----")
               (seq "-----A-----"))
         (create-diamond-from
           (list (seq "-----A-----")
                 (seq "----B-B----")
                 (seq "---C---C---")
                 (seq "--D-----D--")
                 (seq "-E-------E-")
                 (seq "F---------F"))))))