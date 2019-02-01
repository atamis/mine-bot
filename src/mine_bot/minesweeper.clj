(ns mine-bot.minesweeper
  (:require [mine-bot.format :as format]))

(defn neighbors-exclusive
  [[x y]]
  [;[(+ x 0) (+ y 0)]

   [(+ x 1) (+ y 0)]
   [(+ x 1) (+ y 1)]
   [(+ x 0) (+ y 1)]

   [(- x 1) (- y 0)]
   [(- x 1) (- y 1)]
   [(- x 0) (- y 1)]

   [(+ x 1) (- y 1)]
   [(- x 1) (+ y 1)]])

(defn minesweeper
  [x y mines]
  (let [mine-coords (->>
                     (range mines)
                     (map (fn [_] [(rand-int x) (rand-int y)]))
                     (into #{}))
        fun (fn [coords] (if (contains? mine-coords coords)
                           :bomb
                           (->> coords
                                neighbors-exclusive
                                (map #(contains? mine-coords %))
                                (filter #(= % true))
                                count)))]

    (->> (range x)
         (map (fn [x]
                (map (fn [y] (fun [x y]))
                     (range y)))))))

(defn board-render
  [board]
  (defn remap [n] (case n 0 " " :bomb "X" n))
  (def cell (comp format/discord-spoiler format/discord-code remap))

  (->> board
       (map (fn [row]
              (clojure.string/join " " (map cell row))))
       (clojure.string/join
        "\n")))

(defn minesweeper-render
  [x y mines]
  (str (format "Minesweeper %sx%s with %s mines\n" x y mines)
       (board-render (minesweeper x y

                                  mines))))

