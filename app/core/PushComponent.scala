package core

import vos.PushRequest

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 12:15 PM
 *
 */
trait PushComponent {
  /**
   * 平台名称
   * @return
   */
  def tellMeYourPlatform: String

  /**
   * 推送
   * @param list
   */
  def push(list: List[PushRequest])

}
