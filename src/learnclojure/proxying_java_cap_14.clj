;; cap 14
;; Java proxying

(ns learnclojure.proxying-java-cap-14)

; some java libraries require to implement or extend abstract classes
; in order to use them, ex: thread api

;; Thread thread = new Thread () {
;;   public void run() {
;;     System.out.println("Running in a thread");
;;   }
;; }

;; no constructor arguments
(def thread (proxy [Thread] []
  (run [] (println "Running in a thread"))))

;; call run method
(.run thread)

(import 'java.util.concurrent.Executors)
(def mypool (Executors/newFixedThreadPool 4))

(.submit mypool thread)

;; using runnable
(def  myrunnable
  (proxy [Runnable] []
     (run [] (println "Running in a thread"))))

(.submit mypool myrunnable)

;; if you only want to implement interfaces
(def myreified (reify
  Comparable
    (compareTo [this other] -1)
  Runnable
    (run [this] (println "Running via reify"))))

(.submit mypool myreified)

