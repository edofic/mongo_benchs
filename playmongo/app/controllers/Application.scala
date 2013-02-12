package controllers

import com.novus.salat._
import com.novus.salat.dao._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import se.radley.plugin.salat._
import play.api._
import play.api.mvc._
import Play.current

object Application extends Controller {

  object dao extends SalatDAO[Game, ObjectId](collection = mongoCollection("games"))
  val empty = MongoDBObject()

  def index = Action {
    dao.findOne(empty) map { bson =>
      Ok(grater[Game].toCompactJSON(bson)) as "application/json"
    } getOrElse NotFound
  }
  
}

case class UserState(user_id: String, lat: Option[Double] = None, lon: Option[Double] = None)

case class Game(_id: ObjectId = new ObjectId,
                game_name: String,
                password: String,
                latitude: Double,
                longitude: Double,
                game_type: String,
                max_players: Int,
                date: Long,
                users: List[UserState])