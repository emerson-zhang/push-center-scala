package core

import vos.App

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
   * @param appName
   * @param appPwd
   * @return
   * @throws IllegalStateException 找不到推送组件信息时抛出此错误
   *                                throws when can't find the information of push components
   *
   */
  @throws[IllegalStateException]("找不到推送组件信息时抛出此错误 \n throws when can't find the information of push components")
  def load(appName: String, appPwd: String): List[PushComponent]


}
