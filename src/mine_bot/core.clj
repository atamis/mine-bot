(ns mine-bot.core
  (:require [discord.bot :as bot]
            [discord.http :as http]
            [mine-bot.minesweeper :as minesweeper]
            [taoensso.timbre :as log])

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
  (str "<" (:mention (:author message)) ">"))

(bot/defcommand mine
  [client message]
  "Generates and posts a minesweeper board, takes \"x y\" coords, optional third argument for number of mines. "
  (prn message)
  (let [[x y mines] (->> (clojure.string/split (:content message) #" ")
                         (filter #(not (= "" %)))
                         (map #(Integer/parseInt %)))
        x (or x 10)
        y (or y 10)
        mines (or mines 10)
        string (minesweeper/minesweeper-render x y mines)]
    (cond
      (< 2000 (count string)) (bot/say (str (respond-mention message) ", board too large."))
      (< 197 (* x y)) (bot/say (str (respond-mention message) ", maximum 197 spoilers per post, board too large."))
      :else (bot/say (minesweeper/minesweeper-render x y mines)))))

(bot/defcommand hello
  [client message]
  "Say hello"
  (bot/say (str "Hello, <" (:mention (:author message)) ">")))

(bot/defhandler debug-handler [prefix client message]
  (log/info prefix message))

(defn -main
  [& args]
  (bot/start))
