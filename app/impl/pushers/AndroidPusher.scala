package impl.pushers

import vos.PushEntry
import core.dto.Output
import scala.Predef.String
import com.gexin.rp.sdk.base.{IPushResult, IIGtPush}
import com.gexin.rp.sdk.http.IGtPush
import org.slf4j.Logger
import com.gexin.rp.sdk.template.NotificationTemplate
import com.gexin.rp.sdk.base.impl.{SingleMessage, Target}
import core.PushFailureException

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 1:19 PM
 *
 */


case class AndroidPusher(appid: String, appKey: String, appSecret: String, api: String) extends AbstractConcurrentPusher {
  private  val RESULT_CODE_SUCCESS: String = "ok"
  private  val RESULT_CODE_KEY: String = "result"

  private val iGtPush: IIGtPush = new IGtPush(api, appKey, appSecret)

  /**
   * 平台名称
   * @return
   */
  override def tellMeYourPlatform: String = "Android"



  /**
   * 创建一个推送任务
   *
   * <p></p>
   * 注意：如果在<b>创建</b>{@code Runnable}对象过程中发生错误（非线程执行错误），如果你愿意将其处理，
   * 可以将错误捕获并包装为 {@link com.hand.push.core.PushFailureException }抛出.
   * 并且，<b>此时无需将产生错误的数据写入{@code output}</b>，系统会自动捕获
   * @param entry
   * @param output
   * @return
   * @throws PushFailureException
   */
  override protected def getTask(entry: PushEntry, output: Output): ()=>Unit =  {
    def run(): Unit = {
      val logger: Logger = getLogger
      try {
        val template: NotificationTemplate = generateNotificationTemplate(entry)
        val target: Target = generateTarget(entry)
        val message: SingleMessage = generateMessage(template)
        val pushResult: IPushResult = iGtPush.pushMessageToSingle(message, target)
        val responseCode: String = pushResult.getResponse.get(RESULT_CODE_KEY).toString
        if (RESULT_CODE_SUCCESS == responseCode) {
          output.addSuccessEntry(entry)
          logger.trace("success: " + entry)
        }
        else {
          logger.error("error! Caused by: " + responseCode + ", data: " + entry)
          output.addErrorEntry(entry, new PushFailureException("error! Caused by: " + responseCode))
        }
      }
      catch {
        case e: Exception => {
          e.printStackTrace()
          logger.error("error! An unexpected exception occurred, I've no idea: " + entry)
          output.addErrorEntry(entry, new PushFailureException("error! An unexpected exception occurred, I've no idea: "))
        }
      }
    }

    run
  }

  /**
   * @param template
   * @return
   */
  private def generateMessage(template: NotificationTemplate): SingleMessage = {
    val message: SingleMessage = new SingleMessage
    message.setData(template)
    message.setOffline(true)
    message.setOfflineExpireTime(72 * 3600 * 1000)
     message
  }

  /**
   * @param ut
   * @return
   */
  private def generateTarget(ut: PushEntry): Target = {
    val target: Target = new Target
    target.setAppId(appid)
    target.setClientId(ut.pushToken)
     target
  }

  /**
   * @return
   */
  private def generateNotificationTemplate(ut: PushEntry): NotificationTemplate = {
    val template: NotificationTemplate = new NotificationTemplate
    template.setAppId(appid)
    template.setAppkey(appKey)
    template.setTitle("新事项")
    template.setText(ut.message)
    template.setLogo("")
    template.setTransmissionType(1)
     template
  }

  /**
   * 对象销毁时清理
   */
  override def cleanUp(): Unit = {
    iGtPush.close()
  }
}

