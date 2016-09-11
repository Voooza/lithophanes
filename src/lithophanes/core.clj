(ns lithophanes.core
  (:use mikera.image.core)
  (:require [mikera.image.colours :refer [components-rgb]])
  (:require [clojure.set :refer [union]])
  (:gen-class))

(def max-depth 4)

(defn south? [pict i]
  (< i (width pict)))

(defn north? [pict i]
  (let [w (width pict) h (height pict)]
    (> i (- (* w h) (inc w)))))

(defn east? [pict i]
  (let [w (width pict) h (height pict)]
    (= (mod (inc i) w) 0)))

(defn west? [pict i]
  (let [w (width pict) h (height pict)]
    (= (mod i w) 0)))

(defn not-north-east?
  "helper composition function for simple use in filters"
  [pict i]
  (not (or (east? pict i) (north? pict i))))

(defn create-faces-for-point
  "Takes index of point and returns list of all faces related to it."
  [pict i])

(defn top-0
  [pict i]
  (println (str "top-0 called: " i))
  [(str "top-0 " i)])

(defn top-1
  [pict i]
  (println (str "top-1 called: " i))
  [(str "top-1 " i)])

;; TODO following functions can be written genericaly
;; if I just provide recepy/function to get to another vertex. 
(defn north-0
  [pict i]
  (println (str "n: " i))
  [(str "n-0 " i)])

(defn north-1
  [pict i]
  (println (str "n: " i))
  [(str "n-1 " i)])

(defn east-0
  [pict i]
  (println (str "e: " i))
  [(str "e-0 " i)])

(defn east-1
  [pict i]
  (println (str "e: " i))
  [(str "e-1 " i)])

(defn south-0
  [pict i]
  (println (str "s: " i))
  [(str "s-0 " i)])

(defn south-1
  [pict i]
  (println (str "s: " i))
  [(str "s-1 " i)])

(defn west-0
  [pict i]
  (println (str "w: " i))
  [(str "w-0 " i)])

(defn west-1
  [pict i]
  (println (str "w: " i))
  [(str "w-1 " i)])


(defn create
  "Iterates points and returns seq of faces."
  [pict]
  (let [pixs (map (comp first components-rgb) (get-pixels pict))
        w (width pict)
        h (height pict)
        l (* w h)
        t (atom [])
        ne? (complement east?)
        nn? (complement north?)]
    (loop [i 0]
      (if (< i l)
        (do
         (if (not-north-east? pict i)
            (do 
              (swap! t conj (top-0 pict i ))
              (swap! t conj (top-1 pict i)))) 
         (if (and (south? pict i) (ne? pict i))
           (do
             (swap! t conj (south-0 pict i))
             (swap! t conj (south-1 pict i))))
         (if (and (east? pict i) (nn? pict i))
           (do
             (swap! t conj (east-0 pict i))
             (swap! t conj (east-1 pict i))))
         (if (and (north? pict i) (ne? pict i))
           (do
             (swap! t conj (north-0 pict i))
             (swap! t conj (north-1 pict i))))
         (if (and (west? pict i) (nn? pict i))
           (do
             (swap! t conj (west-0 pict i))
             (swap! t conj (west-1 pict i))))
         (recur (inc i)))))
      @t))

    
(defn create-model
  "Takes the picture and returns collection representing stl model."
  [pict]
  )

(defn write-out
  "takes the collection representing the model and writes the stl to the file"
  [model file])

(defn -main
  "Entry point. Takes INput file and OUTput file. "
  [in out & args]
  (let [pict (load-image in)]
    (write-out (create-model pict) out)))




(defn runner
  [in]
  (let [pict (load-image in)
        t (create pict)]
    
    (println (count t))
    t)
  )
