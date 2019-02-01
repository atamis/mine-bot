(ns mine-bot.format)

(defn discord-spoiler
  [s]
  (str "||" s "||"))

(defn discord-code
  [s]
  (str "`", s "`"))
