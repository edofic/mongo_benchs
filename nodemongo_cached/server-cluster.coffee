cluster = require "cluster"
numCPUs = (require "os").cpus().length
http = require "http"
MongoClient = (require "mongodb").MongoClient

withCollection = (collectionName, callback) => 
  MongoClient.connect "mongodb://localhost:27017/urban", (err,db) ->
    if err then throw err
    console.log "connected"
    collection = db.collection collectionName
    callback (db.collection collectionName)


createServer = (port) ->
  withCollection "games", (collection) ->
    cache = undefined 
    server = http.createServer (req, res) -> 
      if(cache)
        res.writeHead 200, "Content-Type": "text/json"
        res.end cache
      else 
        collection.findOne {}, (err,item) ->
          if err
            console.log "error: "+err
            res.writeHead 404
          else 
            res.writeHead 200, "Content-Type": "text/json"
            cache = JSON.stringify item
            res.end cache
    server.listen port 

if cluster.isMaster
  cluster.fork() for i in [1..numCPUs]
else
  createServer 8080




  
