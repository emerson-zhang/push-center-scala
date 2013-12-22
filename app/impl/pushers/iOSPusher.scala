package impl.pushers

import core.PushComponent
import vos.PushRequest

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 1:20 PM
 *
 */

case class iOSPusher(name: String, secret: String, p12FilPath: String, api: String) extends PushComponent {
  /**
   * 平台名称
   * @return
   */
  override def tellMeYourPlatform: String = name //"iOS"

  /**
   * 推送
   * @param list
   */
  override def push(list: List[PushRequest]): Unit = ???
}
