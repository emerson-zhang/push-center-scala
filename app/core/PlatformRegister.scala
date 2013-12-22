package core

import vos.PushApp
import core.dto.AppChannel

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 12:18 PM
 *
 */
trait PlatformRegister {

  /**
   *
   * @param app
   * @return
   * @throws NoSuchElementException 找不到推送组件信息时抛出此错误
   *                                throws when can't find the information of push components
   *
   */
  @throws[NoSuchElementException]("找不到注册的app信息时抛出此错误 \n throws when can't find the information of push components")
  def loadApp(app: PushApp): AppChannel


}
