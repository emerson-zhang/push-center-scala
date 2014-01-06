package impl.pushers

import core.Pusher
import vos.PushEntry

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 1:19 PM
 *
 */


case class AndroidPusher(appid: String, appKey: String, appSecret: String, api: String) extends Pusher {
  /**
   * 平台名称
   * @return
   */
  override def tellMeYourPlatform: String = "Android"

  /**
   * 推送
   * @param list
   */
  override def push(list: List[PushEntry]): Unit = ???
}

