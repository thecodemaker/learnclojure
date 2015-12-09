(ns learnclojure.test.test
  (:require [clojure.test :refer [is]]))


(is (= (+ 1 1) 2)) ; => true
(is (= (+ 1 1) 1)) ; => false and prints the failure to console

; is can be used with any expression which returns a boolean

(is (nil? (first ["a"]))); => false

; expects an exception
(is (thrown? ArithmeticException) (/ 1 1)) ; => nil

; a test can be defined with deftest
(ns learnclojure.test.test
  (:require [clojure.test :refer [is deftest testing]]))

(deftest my-test
  (testing "1 + 1 = 1"
    (is (= (+ 1 1 ) 1))))

; run lein test
; output
; FAIL in clojure.lang.PersistentList$EmptyList@1 (test.clj:6)
; expected: (= (+ 1 1) 1)
;   actual: (not (= 2 1))

; multiple testing suites can be added in the test

(deftest my-test
  (testing "1 + 1 = 1"
    (is (= (+ 1 1 ) 1)))

  (testing "2 - 1 = 1"
    (is (= (- 2 1) 1)))
  )
