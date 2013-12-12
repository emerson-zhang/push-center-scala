package impl

import core.{PushComponent, PlatformRegister}
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.Play
import scala.io.Source
import play.api.Play.current
import impl.pushers.{iOSPusher, AndroidPusher}

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 12:29 PM
 *
 */

class PlatformRegisterImpl extends PlatformRegister {
  /**
   *
   * @param appName
   * @param appPwd
   * @return
   * @throws IllegalStateException 找不到推送组件信息时抛出此错误
   *                               throws when can't find the information of push components
   *
   */
  @throws[IllegalStateException]("找不到推送组件信息时抛出此错误 \n throws when can't find the information of push components")
  override def load(appName: String, appPwd: String): List[PushComponent] = {

    def loadConfigJSFile: String = {
      val configFile = Play.getFile("conf/DevicePlatforms")

      (for (line <- Source.fromFile(configFile).getLines()) yield line).mkString("")

    }

    val configJSON = Json.parse(loadConfigJSFile)




    //    println(configJSON.transform( __.read[Map[String,Any]] ))

    val s = configJSON.validate[List[AndroidPusher]]
    println(s)






    List.empty[PushComponent]


  }

  //  implicit def componentReads:Reads[List[PushComponent]]=

  implicit def androidConfigReads: Reads[AndroidPusher] = (
    (__ \ "name").read[String] and (__ \ "appid").read[String] and (__ \ "appKey").read[String] and (__ \ "appSecret").read[String] and (__ \ "api").read[String]
    )(AndroidPusher.apply _)

  implicit def iOSConfigReads: Reads[iOSPusher] = (
    (__ \ "name").read[String] and (__ \ "secret").read[String] and (__ \ "p12FilPath").read[String] and (__ \ "api").read[String]
    )(iOSPusher.apply _)


   case class Registrion(id: String, pwd: String, name: String, platforms: List[PushComponent])

  implicit def sdf: Reads[List[AndroidPusher]] = (__ \\ "platforms").read[List[AndroidPusher]]


}


/*
{
        "id": "aerwr",
        "pwd": "afasdfasdr",
        "name": "ERSS企业信息订阅",
        "platforms": [
            {
                "name": "Android",
                "appid": "Ytk0ePKK6k65cRd8BGRN17",
                "appKey": "tYaDvmhEbj8VTgQYZaEJP4",
                "appSecret": "xFrLUHKBEsAbkxlGKRsTu6",
                "api": "http://sdk.open.api.igexin.com/apiex.htm"
            },
            {
                "name": "iOS",
                "secret": "tYaDvmhEbj8VTgQYZaEJP4",
                "p12FilPath": "/home/emerson/dev",
                "api": "http://sdk.open.api.igexin.com/apiex.htm"
            }
        ]
    }
 */



