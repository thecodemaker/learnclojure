;; Atoms
;; Cap 18
(ns learnclojure.atoms-cap-18)

;; built-in software transactional memory
;; is designed to share data between threads
;; provides a safe way to implement states with mutable objects

;; atoms provide syncronous transactional access to single values

;; atom function
;; assigned to value count
(def count (atom 0))

;; set value of an atom
(reset! count 1)

;; get value of an atom
(deref count)

;; or deref short hand @
@count

(def count (atom 0))
(dotimes [_ 1000]
  (future (reset! count (inc @count))))

;; it is not a 1000 as expected
;; because of multi threading
@count                                                      ; => 950

;; instead of reset use swap
(def count (atom 0))
(dotimes [_ 1000]
  (future (swap! count inc )))

;; swap works by adding the functions to a queue
;; that gets called on count
;; and count syncronizes all the incomming swaps
@count                                                      ; => 1000

;; swap can accep other functions
(def count (atom 0))
(dotimes [_ 1000]
  (future (swap! count + 2)))

@count                                                      ; => 2000

;; a validator can be added
(def count (atom 0 :validator integer?))
