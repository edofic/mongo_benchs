http = require "http"
MongoClient = (require "mongodb").MongoClient

withCollection = (collectionName, callback) => 
  MongoClient.connect "mongodb://localhost:27017/urban", (err,db) ->
    if err then throw err
    console.log "connected"
    collection = db.collection collectionName
    callback (db.collection collectionName)


withCollection "games", (collection) ->
  server = http.createServer (req, res) -> 
    collection.findOne {}, (err,item) ->
      if err
        console.log "error: "+err
        res.writeHead 404
      else 
        res.writeHead 200, "Content-Type": "text/json"
        res.end (JSON.stringify item)
  server.listen 8080
