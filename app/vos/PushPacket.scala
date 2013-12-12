package vos

import play.api.libs.json._
import play.api.libs.functional.syntax._
/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 9:53 AM
 *
 */


/*

{
  "app": {
    "key": "iiae7893493",
    "secret": "werdfwserw"
  },
  "data": [
    {
      "platform": "iOS",
      "token": "ikqwer394jskjfksjdf",
      "count": 3,
      "message": "hi"
    },
    {
      "platform": "Android",
      "token": "ikqwer394jskjfksjdf",
      "count": 3,
      "message": "hi"
    }
  ]
}
 */
case class PushPacket(app: App, data: List[PushRequest])



object PushPacket {
  implicit def pushPacketRead: Reads[PushPacket] = (
    (__ \ "app").read[App] and (__ \ "data").read[List[PushRequest]]
    )(PushPacket.apply _)



}


case class App(key: String, secret: String) {}

object App {

  implicit def appReads: Reads[App] = (
    (__ \ "key").read[String] and (__ \ "secret").read[String]
    )(App.apply _)
}

case class PushRequest(deviceType: String, pushToken: String, count: Int, message: String) {}

object PushRequest {
  implicit def pushRequestReads: Reads[PushRequest] = (
    (__ \ "platform").read[String] and (__ \ "token").read[String] and (__ \ "count").read[Int] and (__ \ "message").read[String]
    )(PushRequest.apply _)

}



