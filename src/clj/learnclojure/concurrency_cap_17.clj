;; cap 17
;; Concurrency
;;;;;;;;;;;;;;;;;;;;;;;

(ns learnclojure.concurrency_cap_17)

;; all clojure function are instances of Runnable
;; they can be passed to a thread pool directly
(instance? Runnable (fn [])) ; => true

;; or they can be used with Thread to do async tasks
(.start (Thread. (fn [] (println "Hello"))))

; use promise
; promise is representing an on going computation

(promise)

(deliver (promise) "Hello")

(defn myslowfn []
  (let [p (promise)]
    (.start
     (Thread. (fn []
                (Thread/sleep 5000)
                (deliver p "Hello"))))
    p))

(myslowfn)

; get the value from myslowfn
; use deref, will block until the value is present
(deref (myslowfn))

;instead of deref @ simbol can be used
@(myslowfn)

; promises are useful when other operation is hapening while
; they are running

(let [p (myslowfn)]
  (println "Waiting for a promise")
  @p)

; future abstract the way of handling promises manually
; you don't need to write functions which return promises

(defn myslowfn []
  (Thread/sleep 5000)
  "Hello")

; this returns the future right away
; doesn't block
(future (myslowfn))

; get the value using the dereferencing @ symbol
@(future (myslowfn))


(defn slowlog [msg]
  (Thread/sleep 5000)
  (print msg))

(defn myfn []
  (future (slowlog "Called myfn"))
  :ok)

; functions return right away
; a few seconds later the log will appear in the console
(myfn)

; delay, simillar to future
(defn myfn []
  (future (slowlog "Called myfn"))
  :ok)

(myfn)

; memoize
(defn fib[n]
  (if (< n 2)
    1
    (+ (fib (- n 1)) (fib (- n 2)))))

(fib 40)

; if we use memoize the result will be much faster
(def fib (memoize fib))

(fib 40)





