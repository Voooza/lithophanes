(ns lithophanes.test
  (:use mikera.image.core)
  (:use lithophanes.core)
  (:gen-class))

(def test-pict-path "test_20x15.jpg")

(defn test-south?
  []
  (let [pict (load-image test-pict-path) w (width pict) h (height pict)]
    (println "testing function south?.")
    (print "testing for falsy  case: ")
    (println (if (south? pict 1) "passed" "failed"))
    (print "testing for truthy case: ")
    (println (if (south? pict (- (* w h) 1 )) "failed" "passed"))))

(defn test-north?
  []
  (let [pict (load-image test-pict-path) w (width pict) h (height pict)]
    (println "testing function north?.")
    (print "testing for falsy  case: ")
    (println (if (north? pict 1) "failed" "passed"))
    (print "testing for truthy case: ")
    (println (if (north? pict (- (* w h) 1 )) "passed" "failed"))))


(defn test-east?
  []
  (let [pict (load-image test-pict-path) w (width pict) h (height pict)]
    (println "testing function east?.")
    (print "testing for falsy  case: ")
    (println (if (east? pict w) "failed" "passed"))
    (print "testing for truthy case: ")
    (print (if (east? pict (dec w)) "passed; " "failed; "))
    (println (if (east? pict (dec (* 2 w))) " passed " " failed ")))
  )

(defn test-west?
  []
  (let [pict (load-image test-pict-path) w (width pict) h (height pict)]
    (println "testing function west?.")
    (print "testing for falsy  case: ")
    (println (if (west? pict (dec w)) "failed" "passed"))
    (print "testing for truthy case: ")
    (let [testval w]
      (print (if (west? pict testval) (str testval " passed; ") (str testval " failed; "))))
    (let [testval (* 2 w)]
      (println (if (west? pict testval) (str testval " passed ") (str testval  " failed ")))))
  )


(defn adder
  [n t]
  (conj (conj t (rand-int 10)) n))

(defn conj-exp
  [a]
  (let [b (atom a)]
    (swap! b adder 2)
    (swap! b adder 3)
    (deref b))
  )

(defn iter-vector
  []
  (let [v (into [] (take 15  (range 2 30 2)))]
    (println (str "v length " (count v)))
    (println v)
    (loop [i 0]
      (if (< i (count v))
        (do 
          (println (nth v i))
          (recur (inc i)))))
    
    (loop [c v]
      (if (not (= (count c) 0))
        (do
          (println (first c))
          (recur (rest c)))))
    )

  )


(defn test
  []
  (test-north?)
  (test-south?)
  (test-east?)
  (test-west?))










