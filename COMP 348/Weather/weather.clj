(ns weather
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(declare main-menu handle-choice)

(defn parse-line
  "Parses a comma-separated line into a weather report map."
  [line]
  (let [[date location temp condition] (str/split line #",")]
    ; split each info by comma into a map
    {:date date
     :location location
     :temperature (Integer/parseInt temp)
     :unit "C"; °C by default
     :condition condition}))

(defn load-reports
  "Loads weather reports from a file."
  [filename]
  (if (.exists (io/file filename))
    (->> (slurp filename) ; read the whole file content as single string
         str/split-lines ; split it into lines
         (map parse-line) ; parse each line (each map report) into a vector
         vec)
    (do
      (println "Error: Weather data file not retrieved.")
      (println "Terminating program...")
      (System/exit 1)))) ; else return empty vector

(defn save-reports
  "Saves weather reports to a file."
  [reports]
  (spit "weather_data.txt"
        (->> reports
             (map #(str (:date %) "," (:location %) "," (:temperature %) "," (:condition %))) ; print each report as a string
             (str/join "\n")))) ; newline

(defn view-weather-reports
  "Displays weather reports in a formatted table."
  [reports]
  (println (str "\nTotal weather reports: " (count reports)))
  (when (seq reports)
    ; Table header
    (println "|------------|--------------|--------------|-----------|")
    (println "|    Date    |   Location   | Temperature  | Condition |")
    (println "|------------|--------------|--------------|-----------|")
    ; Reports: table rows
    (doseq [r (sort-by :date reports)]
      (println (format "| %-10s | %-12s | %12s | %-9s |"
                       (:date r)
                       (:location r)
                       (str (:temperature r) "°" (:unit r))
                       (:condition r))))))

(defn transform-temperature-units
  "Transforms temperature units for all reports."
  [reports]
  (println "\n1. Celsius to Fahrenheit")
  (println "2. Fahrenheit to Celsius")
  (print "Enter your choice (1-2): ")
  (flush) ; flush the output buffer (prompt is displayed before reading input)
  (let [choice (read-line)]
    (cond
      (= choice "1")
      (mapv (fn [single-report]
              (if (= (:unit single-report) "C")
                (assoc single-report :temperature (int (+ (* (/ 9 5) (:temperature single-report)) 32)) :unit "F")
                single-report)) ; else: returns it unchanged if not in Celsius
            reports)

      (= choice "2")
      (mapv (fn [r]
              (if (= (:unit r) "F")
                (assoc r :temperature (int (/ (* (- (:temperature r) 32) 5) 9)) :unit "C")
                r))
            reports)

      :else (do (println "Invalid choice.") reports))))

(defn filter-weather-reports
  "Filters reports by condition or temperature range."
  [reports]
  (println "\n1. Filter by Condition")
  (println "2. Filter by Temperature Range")
  (print "Enter your choice (1-2): ")
  (flush)
  (let [choice (read-line)]
    (cond
      (= choice "1")
      (do
        (print "Enter condition: ")
        (flush)
        (let [cond-input (str/lower-case (read-line))
              filtered (filter #(= (str/lower-case (:condition %)) cond-input) ; check if lower case condition matches
                               reports)]
          (if (seq filtered)
            (view-weather-reports filtered)
            (println "No matching reports."))))

      (= choice "2")
      (do
        (print "Enter min temperature: ")
        (flush)
        (let [min-temp (Integer/parseInt (read-line))]
          (print "Enter max temperature: ") (flush)
          (let [max-temp (Integer/parseInt (read-line))
                filtered (filter #(and (>= (:temperature %) min-temp)
                                       (<= (:temperature %) max-temp)) ; check temperature range
                                 reports)] ; apply filter to reports and store in filtered
            (if (seq filtered) ; check if not empty
              (view-weather-reports filtered)
              (println "No matching reports."))))) ; else

      :else (println "Invalid option."))))

(defn weather-statistics
  "Prints weather statistics: average, hottest, coldest, unique conditions."
  [reports]
  (let [temps (map :temperature reports) ; extract temperatures from reports into a list
        avg (double (/ (reduce + temps) (count temps)))
        hottest (apply max-key :temperature reports) ; using built-in max-key function (key = temperature)
        coldest (apply min-key :temperature reports) ; using built-in min-key
        unique-conditions (count (set (map :condition reports)))]
    (println (format "\nAverage Temperature: %.2f" avg))
    (println "Hottest Day:")
    (println (format "| %-10s | %-12s | %12s | %-9s |"
                     (:date hottest)
                     (:location hottest)
                     (str (:temperature hottest) "°" (:unit hottest))
                     (:condition hottest)))
    (println "Coldest Day:")
    (println (format "| %-10s | %-12s | %12s | %-9s |"
                      (:date coldest)
                      (:location coldest)
                      (str (:temperature coldest) "°" (:unit coldest))
                      (:condition coldest)))
    (println (str "Unique Weather Conditions: " unique-conditions))))

(defn exit-program []
  (println "\nThank you for using the Weather Report System. Goodbye!")
  (System/exit 0))

(defn main-menu
  ([file]
   (main-menu file (load-reports file)))
  ([file reports]
   (println "\n=== Weather Report System ===")
   (println "1. View Weather Reports")
   (println "2. Transform Weather Report")
   (println "3. Filter Weather Reports")
   (println "4. Weather Statistics")
   (println "5. Save and Exit")
   (print "Enter your choice (1-5): ")
   (flush)
   (let [choice (read-line)]
     (handle-choice choice reports file))))

(defn handle-choice [choice reports file]
  (case choice
    "1" (do (view-weather-reports reports)
            (main-menu file reports))
    "2" (let [updated (transform-temperature-units reports)]
          (main-menu file updated))
    "3" (do (filter-weather-reports reports)
            (main-menu file reports))
    "4" (do (weather-statistics reports)
            (main-menu file reports))
    "5" (do (save-reports reports)
            (exit-program))
    (do (println "Invalid option. Try again.")
        (main-menu file reports))))

;; Entry point
(defn -main [& _args]
  (let [file "weather_data.txt"]
    (main-menu file)))

(-main)