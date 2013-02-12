package controllers

import play.api._
import cache.Cached
import play.api.mvc._

import play.api.libs.concurrent.Execution.Implicits.defaultContext

// Reactive Mongo imports
import reactivemongo.api._
import reactivemongo.bson._
import reactivemongo.bson.handlers.DefaultBSONHandlers._

// Reactive Mongo plugin
import play.modules.reactivemongo._
import play.modules.reactivemongo.PlayBsonImplicits._

// Play Json imports
import play.api.libs.json._

import play.api.Play.current

object Application extends Controller {

  lazy val collection = ReactiveMongoPlugin.collection("games")
  val all = QueryBuilder()
  
  def index = Cached("response"){
    Action {
      Async{
        collection.find[JsValue](all).headOption() map {
          opt => Ok(opt getOrElse JsString("error"))
        }
      }
    }
  }
}
