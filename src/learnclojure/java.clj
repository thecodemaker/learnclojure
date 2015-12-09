;  cap 13
;  Java
;;;;;;;;;;;;;;;;;

(ns learnclojure.java)

; Java has a huge and useful standard library, so
; you'll want to learn how to get at it.

; Use import to load a java module
(import java.util.Date)

; You can import from an ns too.
(ns test
  (:import java.util.Date
           java.util.Calendar))

; import java classes
; multi import java class syntax
(ns learnclojure.java
  (:import [java.util Date Calendar]))

; all classes from java standard library are imported at runtime

; Use / to call static methods
(System/currentTimeMillis) ; <a timestamp> (system is always present)

; Use the class name with a "." at the end to make a new instance
(Date.) ; <a date object>

; Use . to call methods. Or, use the ".method" shortcut
(. (Date.) getTime) ; <a timestamp>
(.getTime (Date.)) ; exactly the same thing.

; Use doto to make dealing with (mutable) classes more tolerable
(import java.util.Calendar)
(doto (Calendar/getInstance)
  (.set 2000 1 1 0 0 0)
  .getTime) ; => A Date. set to 2000-01-01 00:00:00

; create array
(int-array 100)
(type (int-array 100)) ; => [I

(object-array 100) ; array of java.lang.Object

; create array of Strings
(def myarray (into-array String ["this" "is" "an" "array"]))

; get element from array
(aget myarray 1) ; => "is"

; set element from array
(aset myarray 1 "was")

; amap to apply map on a java array
; areduce to apply reduce on a java array

; type hinting
; provide a value which will allow to the clojure compiler to do some optimizations

(defn strlen [^String s] (.length s))
(defn badstrlen [s] (.length s))

(time (reduce + (map badstrlen (repeat 100000 "abcdefg")))) ; console: "Elapsed time: 192.663388 msecs"
(time (reduce + (map strlen (repeat 100000 "abcdefg")))) ; console: "Elapsed time: 17.746704 msecs"

; warn on reflection flag
(set! *warn-on-reflection* true)
