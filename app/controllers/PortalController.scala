package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import impl.{BeanConfiguration, AppRegisterImpl}
import core.dto.AppChannel
import vos.{PushEntry, PushPacket, PushApp}


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
           "app":{"key":"HR","secret":"handhand"},
           "data":[
             {"platform":"iOS","token":"ikqwer394jskjfksjdf","count":3,"message":"hi"},
             {"platform":"Android","token":"ikqwer394jskjfksjdf","count":3,"message":"hi"}
           ]
         }
      """)

    val reg = BeanConfiguration.configAppRegister
    //    val s = json.validate[PushPacket]
    val s = json.validate[PushPacket].map {
      packet => reg.loadApp(packet.app)


    }




    Ok(json)
  }

}
