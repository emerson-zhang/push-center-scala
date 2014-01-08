package impl

import core.{AppNotFoundException, AppRegister}
import core.dto.AppChannel
import vos.PushApp

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 12:29 PM
 *
 */

class AppRegisterImpl(apps: List[AppChannel]) extends AppRegister {

  @throws[IllegalStateException]("找不到推送组件信息时抛出此错误 <p/> throws when can't find the information of push components")
  override def loadApp(app: PushApp): AppChannel = apps.find {
    ch =>
      (ch.appKey == app.key) && (ch.appSecret == app.secret)

  }.getOrElse(throw new AppNotFoundException("未找到需要的app: " + app))


}

object AppRegisterImpl {
  def apply(apps: List[AppChannel]) = new AppRegisterImpl(apps)
}



