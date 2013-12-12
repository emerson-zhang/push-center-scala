package impl.pushers

import core.PushComponent
import vos.PushRequest

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 1:19 PM
 *
 */


case class AndroidPusher(name:String,appid:String,appKey:String,appSecret:String,api:String) extends PushComponent{
  /**
   * 平台名称
   * @return
   */
  def tellMeYourPlatform: String = name //Android

  /**
   * 推送
   * @param list
   */
  def push(list: PushRequest): Unit = ???
}

