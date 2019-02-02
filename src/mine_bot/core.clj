(ns mine-bot.core
  (:require [discord.bot :as bot]
            [discord.http :as http]
            [mine-bot.minesweeper :as minesweeper])
  (:gen-class))

(comment
  :say
  (fn [client message]
    (println "Recieved" message)
    (comment (:content
              message))
    (bot/say (:content message))))

(defn respond-mention
  [message]
  (str "<" (:mention (:author message)) ">")
  )

(defn minesweeper-extension
  [client message]
  (let [[x y mines] (->> (clojure.string/split (:content message) #" ")
                         (map #(Integer/parseInt %)))
        x (or x 10)
        y (or y 10)
        mines (or mines 10)
        string (minesweeper/minesweeper-render x y mines)
        ]
    (cond

      (< 2000 (count string)) (bot/say (str (respond-mention message) ", board too large."))
      (< 197 (* x y)) (bot/say (str (respond-mention message) ", maximum 197 spoilers per post, board too large."))
      :else (bot/say (minesweeper/minesweeper-render x y mines))
      )
    ))

(defn -main
  "Discord bot to generate minesweeper boards."
  [& args]
  (println "Launching...")
  (bot/with-extensions
    "MinesweeperBot" "^"
    :hello (fn [client message] (println "Received" message)
             (bot/say (str "Hello, <" (:mention (:author message)) ">")))
    :mine minesweeper-extension))
