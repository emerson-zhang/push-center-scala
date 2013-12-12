package controllers

import play.api._
import play.api.mvc._
import vos.{PushPacket}
import play.api.libs.json.{Reads, Json}
import impl.PlatformRegisterImpl
import PushPacket.pushPacketRead


/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/11/13
 * Time: 1:31 PM
 *
 */
object PortalController extends Controller{

  def push() = Action {
    val json =  Json.parse("""{"app":{"key":"iiae7893493","secret":"werdfwserw"},"data":[{"platform":"iOS","token":"ikqwer394jskjfksjdf","count":3,"message":"hi"},{"platform":"Android","token":"ikqwer394jskjfksjdf","count":3,"message":"hi"}]}""")

//    val s = json.validate[PushPacket]
    val s = json.as[PushPacket]
//    println(s)
    println(s)

    val reg = new PlatformRegisterImpl
    reg.load("aerwr","afasdfasr")

    Ok(json)
  }

}
