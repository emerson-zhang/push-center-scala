package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import impl.PlatformRegisterImpl
import core.dto.AppChannel
import vos.{PushRequest, PushPacket, PushApp}


/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/11/13
 * Time: 1:31 PM
 *
 */
object PortalController extends Controller {

  def push() = Action {
    val json = Json.parse(
      """
         {
           "app":{"key":"iiae7893493","secret":"werdfwserw"},
           "data":[
             {"platform":"iOS","token":"ikqwer394jskjfksjdf","count":3,"message":"hi"},
             {"platform":"Android","token":"ikqwer394jskjfksjdf","count":3,"message":"hi"}
           ]
         }
      """)

    val reg = new PlatformRegisterImpl(List.empty[AppChannel])
    //    val s = json.validate[PushPacket]
    val s = json.validate[PushPacket].map {
      packet => reg.loadApp(packet.app)


    }
    //    println(s)
    println(s)


    reg.loadApp(PushApp("",""))



    Ok(json)
  }

}
