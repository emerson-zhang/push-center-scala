package impl.pushers

import vos.PushEntry
import core.dto.Output
import org.slf4j.Logger

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable
import javapns.notification._
import javapns.devices._
import javapns.devices.implementations.basic.BasicDevice
import javapns.communication.exceptions.CommunicationException

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 1:20 PM
 *
 */

case class iOSPusher(sound: String = "default", certificatePath: String, certificatePassword: String, isProduct: Boolean = true) extends AbstractConcurrentPusher {
  /**
   * 平台名称
   * @return
   */
  override def tellMeYourPlatform: String = "iphone"

  /**
   * 对象销毁时清理
   */
  override def cleanUp(): Unit = {

  }

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
  override protected def getTask(entry: PushEntry, output: Output): () => Unit = {

    def run(): Unit = {

      import scala.collection.JavaConversions._
      val logger: Logger = getLogger
      try {

        val pushManager: PushNotificationManager = initPushManager


        val alert: String = entry.message
        val badge: Int = entry.count
        val payLoad: PushNotificationPayload = new PushNotificationPayload(alert, badge, sound)
        val device: Device = new BasicDevice
        device.setToken(entry.pushToken)
        val notification: PushedNotification = pushManager.sendNotification(device, payLoad, true)
        val notifications: mutable.Buffer[PushedNotification] = ArrayBuffer.empty[PushedNotification]
        notifications += notification
        val successfulNotifications: mutable.Seq[PushedNotification] = PushedNotification.findSuccessfulNotifications(notifications)
        for (successfulNotification <- successfulNotifications) {
          logger.trace("success: " + entry)
          output.addSuccessEntry(entry)
        }
        val failedNotifications: mutable.Seq[PushedNotification] = PushedNotification.findFailedNotifications(notifications)
        for (failedNotification <- failedNotifications) {
          logger.error("error! Caused by: " + failedNotification.getException + ", data: " + entry)
          output.addErrorEntry(entry, failedNotification.getException)
        }
      }
      catch {
        case e: CommunicationException => {
          e.printStackTrace
          logger.error("error! Caused by: " + e)
          output.addErrorEntry(entry, e)
        }
        case e: Exception => {
          logger.error("error! Caused by: " + e)
          e.printStackTrace
          output.addErrorEntry(entry, e)
        }
      }

    }

    run
  }

  private def initPushManager: PushNotificationManager = {
    getLogger.info("init PushNotificationManager, certificatePath: " + certificatePath)
    val pushManager: PushNotificationManager = new PushNotificationManager
    pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, isProduct))
    pushManager
  }
}
