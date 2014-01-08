package controllers

import play.api.mvc._
import play.api.libs.json._
import impl.BeanConfiguration
import vos.PushPacket
import core.dto.{OutputImp, Bundle}


/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/11/13
 * Time: 1:31 PM
 *
 */
object PortalController extends Controller {

  val chain = BeanConfiguration.processorChain()

  def push() = Action {
    val json = Json.parse(
      """
         {
           "app":{"key":"HR","secret":"handhand"},
           "data":[
             {"platform":"iphone","token":"ikqwer394jskjfksjdf","count":3,"message":"hi"},
             {"platform":"Android","token":"ikqwer394jskjfksjdf","count":3,"message":"hi"}
           ]
         }
      """)

    val s = json.validate[PushPacket].map {
      packet =>
        chain.process(Bundle(packet, System.currentTimeMillis().toString, OutputImp()))
    }.getOrElse(throw new RuntimeException)



    Ok(json)
  }

}
