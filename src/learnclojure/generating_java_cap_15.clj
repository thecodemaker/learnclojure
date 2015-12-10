;; cap 15
;; Generating java
;;;;;;;;;;;;;;;;;;;;;;;;;

(ns learnclojure.generating_java_cap_15)

; it is a macro
; it takes a lot of parameters
(gen-class
   :name learnclojure.MyClass
   :prefix "my-"
   :methods [[getName [] String ]]
   :constructor {[String] []}
   :state state
   :init init)

(defn my-init [name]
  [[] {:name name}])

(defn my-getName [this]
  (get (.state name) :name))


;; use :aot 'ahead of its time' to compile
;; :aot [learnclojure.generating_java_cap_15]

;; thecodemaker@play:~/IdeaProjects/learnclojure$ lein repl
;; Compiling learnclojure.generating_java_cap_15

;; use lein repls to start a repl
;; lein repl

;; learnclojure.core=> (import 'learnclojure.MyClass)
;; learnclojure.MyClass
;; learnclojure.core=> (.getName (MyClass.))
;; "MyClass's Name"
