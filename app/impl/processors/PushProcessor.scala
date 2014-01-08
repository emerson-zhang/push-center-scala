package impl.processors

import core._
import vos.PushEntry
import scala.collection.mutable
import scala.collection.immutable.Seq
import scala.Predef._
import core.dto.Bundle

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 1/6/14
 * Time: 4:09 PM
 *
 */
class PushProcessor(appRegister: AppRegister) extends Processor with LoggerTrait {


  def process(bundle: Bundle): Unit = {
    val logger = getLogger

    logger.debug("PushProcessor received bundle")

    //1.根据app，找到推送配置
    val requestApp = bundle.pushPacket.app

    //2.获取推送器
    val appChannel = appRegister.loadApp(requestApp)
    logger.trace("appChannel acquired :" + appChannel)


    //3. 将推送数据分组
    val groupedRequest = groupRequests(bundle.pushPacket.entries)
    logger.trace("grouped push requests: " + groupedRequest)


    //4. 选择推送器推送
    groupedRequest.map {
      group => {
        try {
          //选择推送器
          val pusher = selectPusher(group._1, appChannel.pushers)

          //推送
          pusher.push(group._2,bundle.output)
        } catch {
          case ex: RuntimeException => logger.error("An unexpected error occurred, I've no idea: " + ex)
        }

      }
    }

    logger.trace("process end")
  }

  /**
   * 将推送数据分组，形成
   * {
   * "Android":[
   * {"message":"..","token":".."},
   * ...
   * ],
   * "iphone":[
   * {"message":"..","token":".."},
   * ...
   * ]
   * }
   * <p/>
   * 格式
   *
   * @param rawRequests
   * @return
   */
  private def groupRequests(rawRequests: List[PushEntry]): Map[String, Vector[PushEntry]] = {

    val result = mutable.HashMap.empty[String, Vector[PushEntry]]
    val keys = rawRequests.map(_.deviceType).distinct

    keys.foreach {
      key =>
        result += (key -> rawRequests.filter(_.deviceType == key).toVector)
    }

    result.toMap[String, Vector[PushEntry]]

  }

  private def selectPusher(platformName: String, pushers: Seq[Pusher]): Pusher = pushers.find(
    _.tellMeYourPlatform == platformName).getOrElse {
    throw new AppNotFoundException(s"未找到要求的推送器，请检查配置 $platformName")
  }


}

