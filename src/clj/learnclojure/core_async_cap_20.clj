;; cap 20
;; core.async
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; newest library that facilitates writing async code
;; asynchronous programming using channels
;; Communicating Sequential Processes

;; dependency required
;; [org.clojure/core.async "0.2.374"]

;; import required
(ns learnclojure.core-async-cap-20
  (:require [clojure.core.async :refer [chan go >! <! go-loop]]))
;; refer chan go put take and go-loop

;; define channel
(def my-chan (chan))

;; it is very simillar to go language

;; take a value from my-chan
(go (println (<! my-chan)))

;; put a message on the channel
(go (>! my-chan "Hello"))

;; loop in a go-block
;; it will read messages forever
(go (loop []
      (println (<! my-chan))
      (recur)))

;; change syntax
(go-loop []
      (println "!" (<! my-chan))
      (recur))

;; blocking versions on take and put
;; be very carreful to not block main tread
(async/>!! my-chan "Hi")


;; writing a function which accepts a channel
(defn print-listener [chan]
  (go-loop []
      (println (<! chan))
      (recur))
  chan)

;; call function
(print-listener my-chan)
(go (>! my-chan "Hello"))

;; pipeline pattern

(defn reverser [in-chan]
  (let [out-chan (chan)]
    (go-loop []
             (>! out-chan (reverse (<! in-chan)))
             (recur))
    out-chan))

(def in-chan (chan))
(def rev-chan (reverser in-chan))
(print-listener rev-chan)

(go (>! in-chan [1 2 3]))
; => [3 2 1]

;; consume from many channels at once
;; alts!

(defn combine-chans [& chans]
  (let [out-chan (chan)]
    (go-loop []
             (let [[value c] (async/alts! chans)]
               (>! out-chan value))
             (recur))
    out-chan))
(def chan1 (chan))
(def chan2 (chan))
(def both-chans (combine-chans chan1 chan2))

(print-listener both-chans)

(go (>! chan2 "Hi chan2"))









