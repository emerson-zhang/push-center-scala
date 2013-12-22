package impl

import core.{PushComponent, PlatformRegister}
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.Play
import scala.io.Source
import play.api.Play.current
import impl.pushers.{iOSPusher, AndroidPusher}
import core.dto.AppChannel
import vos.PushApp

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 12:29 PM
 *
 */

class PlatformRegisterImpl(val apps: List[AppChannel]) extends PlatformRegister {

  @throws[IllegalStateException]("找不到推送组件信息时抛出此错误 \n throws when can't find the information of push components")
  override def loadApp(app: PushApp): AppChannel = {

    def loadConfigJSFile: String = {
      val configFile = Play.getFile("conf/DevicePlatforms")

      (for (line <- Source.fromFile(configFile).getLines()) yield line).mkString("")

    }

    val configJSON = Json.parse(loadConfigJSFile)
    println(configJSON)

    null


  }


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



