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
case class PushPacket(app: PushApp, entries: List[PushEntry])


object PushPacket {
  implicit def pushPacketRead: Reads[PushPacket] = (
    (__ \ "app").read[PushApp] and (__ \ "data").read[List[PushEntry]]
    )(PushPacket.apply _)


}


case class PushEntry(deviceType: String, pushToken: String, count: Int, message: String) {}

object PushEntry {
  implicit def pushRequestReads: Reads[PushEntry] = (
    (__ \ "platform").read[String] and (__ \ "token").read[String] and (__ \ "count").read[Int] and (__ \ "message").read[String]
    )(PushEntry.apply _)

}

case class PushApp(key: String, secret: String)

object PushApp {

  implicit def appReads: Reads[PushApp] = (
    (__ \ "key").read[String] and (__ \ "secret").read[String]
    )(PushApp.apply _)
}



