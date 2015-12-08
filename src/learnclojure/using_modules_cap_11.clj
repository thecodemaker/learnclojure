;; see https://github.com/thecodemaker/learnclojure
;; cap 11 - import external sources/modules and use them
(require 'clojure.string)
(clojure.string/split "a,b,c" #",") ; => ["a" "b" "c"]

; to be able to use split withouth specifing the namespace
(require '[clojure.string :refer [split]])
(split "a,b,c" #",") ; => ["a" "b" "c"]

; to be able to refer all functions from a namespace use :all
; not recommanded since it is difficult to track down the functions
; #"," - is a regular expression
(require '[clojure.string :refer :all])
(split "a,b,c" #",") ; => ["a" "b" "c"]

; You can give a module a shorter name on import
(require '[clojure.string :as str])
(str/replace "This is a test." #"[a-o]" str/upper-case) ; => "THIs Is A tEst."
; (#"" denotes a regular expression literal)

; You can use require (and use, but don't) from a namespace using :require.
; You don't need to quote your modules if you do it this way.
(ns test
    (:require
      [clojure.string :as str]
      [clojure.set :as set]))
