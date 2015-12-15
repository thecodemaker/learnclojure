;; cap 19
;; Refs and Agents
;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns learnclojure.refs-and-agents-cap-19)

(def account1 (atom 0 :validator #(>= % 0)))
(def account2 (atom 0 :validator #(>= % 0)))

(defn transfer [from-account to-account amount]
  (swap! to-account + amount)
  (swap! from-account - amount))

(reset! account2 1000)
(dotimes [_ 1000]
  (future (transfer account2 account1 100)))


;; we have not interacted with the accounts in a transactional way
;; we have done two separate interactions
(deref account1) ; => 99200
(deref account2)

;; instead we can put memory changes in a logical transactions
;; we can use a ref instead of an atom
;; difference between a ref and atom
;; refs instead of swap use alter
(def account1 (ref 1000 :validator #(>= % 0)))
(def account2 (ref 1000 :validator #(>= % 0)))

(defn transfer [from-account to-account amount]
  (dosync
    (alter to-account + amount)
    (alter from-account - amount)))

(dotimes [_ 1000]
  (future (transfer account2 account1 100)))


;; we have not interacted with the accounts in a transactional way
;; we have done two separate interactions
(deref account1) ; => 2000
(deref account2)

;; instead of alter commute can be used
;; commute can happen in parallel at the end of the transaction
;; will result in a better performance
;; it will not lock memory for a commute

(def account1 (ref 1000 :validator #(>= % 0)))
(def account2 (ref 1000 :validator #(>= % 0)))

(defn transfer [from-account to-account amount]
  (dosync
    (commute to-account + amount)
    (commute from-account - amount)))

(dotimes [_ 1000]
  (future (transfer account2 account1 100)))


;; we have not interacted with the accounts in a transactional way
;; we have done two separate interactions
(deref account1) ; => 2000
(deref account2)

;; the oposit of refs are agents
;; agents are async updates on single values
(def my-agent (agent 0 :validator #(>= % 0)))

;; it is asyncronous
(send my-agent inc)

(deref my-agent) ;=> unexpected result

;; it is difficult to handle errors
;; because it doesn't happen right away
;; to recover a failed agent you can use restart-agent
(restart-agent my-agent 0)
;; it can be set to ignore
(def my-agent (agent 0 :validator #(>= % 0) :error-mode :continue))

;; it can have an error handler
(def my-agent (agent 0 :validator #(>= % 0)
                     :error-mode :continue
                     :error-handler println))



