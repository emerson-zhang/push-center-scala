package core

import vos.PushEntry
import core.dto.Output

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 12:15 PM
 *
 */
trait Pusher {
  /**
   * 平台名称
   * @return
   */
  def tellMeYourPlatform: String

  /**
   * 推送
   * @param list
   */
  def push(list: Vector[PushEntry], output:Output)

}
