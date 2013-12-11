package controllers

import play.api._
import play.api.mvc._
import vos.UserPushToken
import vos.UserPushToken.pushVOReads
import vos.UserPushToken.pushToList
import play.api.libs.json.{Reads, Json}


/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/11/13
 * Time: 1:31 PM
 *
 */
object PortalController extends Controller{

  def push() = Action {
    val json =  Json.obj("pushToken"->"asdfasdf","message"-> "String", "deviceType"->"Android" , "count"-> 0)

    val jsons  = Json.arr(
      Json.obj("pushToken"->"asdfasdf","message"-> "String", "deviceType"->"Android" , "count"-> 0),
      Json.obj("pushToken"->"asdfasdf","message"-> "String", "deviceType"->"iOS" , "count"-> 20)
    )

    println(jsons)
    val s = json.validate[UserPushToken]
    println(s)
    println(s.get)

    val xs:Reads[List[UserPushToken]] = jsons
    println(xs)
    Ok(json)
  }

}
