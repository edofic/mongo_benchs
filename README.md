some tests to compare diffrent approaches to serve content from mongo

run this in mongo db shell to prepare data

use urban

db.games.insert({ "_id" : ObjectId("50eb1ed2e4b05a6ea23f6e3c"), "date" : NumberLong("1357585844669"), "game_name" : "fun game", "game_type" : "test", "latitude" : 0, "longitude" : 0, "max_players" : 1, "password" : "secret", "users" : [ { "lat" : 12.3, "lon" : 45.5, "user_id" : "50eb1ed2e4b05a6ea23f6e3b" }, { "user_id" : "50eb1fc8e4b05a6ea23f6e3d" } ] })

