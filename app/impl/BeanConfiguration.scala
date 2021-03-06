package impl

import core.{AppRegister, Processor, ProcessorChain}
import impl.processors.PushProcessor
import core.dto.AppChannel
import impl.pushers.{AndroidPusher, iOSPusher}
import play.api.Play
import play.api.Play.current

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 1/6/14
 * Time: 4:05 PM
 *
 */

//TODO 考虑单例？
object BeanConfiguration {
  /**
   * 配置链条
   * @return
   */
  def processorChain(): ProcessorChain = new ProcessorChain(processors())


  /**
   * 配置所有节点
   * @return
   */
  def processors(): List[Processor] = {
    /**
     * 配置推送节点
     * @return
     */
    def pushProcessor: PushProcessor = new PushProcessor(configAppRegister)

    List(pushProcessor)

  }

  /**
   * 配置应用管理器
   * @return
   */
  def configAppRegister: AppRegister = AppRegisterImpl(configApps)


  /**
   * 配置应用通道
   * @return
   */
  def configApps: List[AppChannel] = {

    /**
     * 配置hr应用通道
     *
     * @return
     */
    def hrApp = {
      val ios = iOSPusher(certificatePath = Play.getFile("conf/pushResource/hr/hmbsng-apns-Production.p12").getAbsolutePath,certificatePassword = "123")
      val android = AndroidPusher(appid = "asdf", appKey = "adf", appSecret = "asdf", api = "http://www.baidu.com")
      AppChannel("HR", "handhand", List(ios, android))


    }

    print(hrApp)

    List(hrApp)
  }
}
