package vos

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/11/13
 * Time: 1:35 PM
 *
 */
case class UserPushToken(message: String,
                         pushToken: String,
                         deviceType: String,
                         count: Int) {


}

object UserPushToken {
  implicit val pushVOReads:Reads[UserPushToken] = (
    (__ \ 'message).read[String]
      and  (__ \ 'pushToken).read[String]
      and (__ \ 'deviceType).read[String]
      and (__ \ "count").read[Int]

    )(UserPushToken.apply _)

  implicit val pushToList:Reads[List[UserPushToken]] = (__ \ "").read[List[UserPushToken]]

}



//new JsSuccess[UserPushToken](UserPushToken(
//(json \ "message").as[String],
//(json \ "pushToken").as[String],
//(json \ "deviceType").as[String],
//(json \ "count").as[Int]
//))


