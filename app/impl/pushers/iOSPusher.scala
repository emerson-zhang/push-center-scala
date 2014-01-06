package impl.pushers

import core.Pusher
import vos.PushEntry

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 1:20 PM
 *
 */

case class iOSPusher(secret: String, p12FilPath: String, api: String) extends Pusher {
  /**
   * 平台名称
   * @return
   */
  override def tellMeYourPlatform: String = "iphone"

  /**
   * 推送
   * @param list
   */
  override def push(list: List[PushEntry]): Unit = ???
}
