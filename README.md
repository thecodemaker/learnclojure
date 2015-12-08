;; previous notes: https://gist.github.com/thecodemaker/36cb7e656cf7434a36c4

;; cap 10
;; setup a clojure project
;; use leiningen - leiningen.org

;; create new project
lein new learnclojure

;; project.clj
   - definition of the versions of software you are using
   - description, name of everything
   - equivalent to the pom.xml maven file

;;code

```clojure
(ns learnclojure.core)

(defn foo[x]
  "I don't di a whole lot"
  (println x "Hello, World!"))

(defn -main []
  (foo "Main"))
```

;; run the main function, it will look for maine by default
lein run -m learnclojure.core
prints: Main Hello, World!

;; setup main class so you don't need to specify it with run command
;; code

```clojure
(defproject learnclojure "1.0.0-SNAPSHOT"
  :description "Learn Clojure"
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :main learnclojure.core)
```

lein run

