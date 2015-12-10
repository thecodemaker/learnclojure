;; cap 16
;; using java code with leniningen
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns learnclojure.using_java_cap_16
  (:import MyClass))

; add a folder called java and one called clj
; in project.clj specify
; :source-paths ["src/clj"]
; :java-source-paths ["src/java"]

; after this disconnect clients CTRL + SPACE

; use the class
(def myinstance (MyClass. "Johnny"))

(.getName myinstance)

; it is easier to use than gen class
