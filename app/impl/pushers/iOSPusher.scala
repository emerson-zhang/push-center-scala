package impl.pushers

import core.Pusher
import vos.PushEntry
import core.dto.Output

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 1:20 PM
 *
 */

case class iOSPusher(secret: String, p12FilPath: String, api: String) extends AbstractConcurrentPusher {
  /**
   * 平台名称
   * @return
   */
  override def tellMeYourPlatform: String = "iphone"

  /**
   * 对象销毁时清理
   */
  def cleanUp(): Unit = {

  }

  /**
   * 创建一个推送任务
   *
   * <p></p>
   * 注意：如果在<b>创建</b>{@code Runnable}对象过程中发生错误（非线程执行错误），如果你愿意将其处理，
   * 可以将错误捕获并包装为 {@link com.hand.push.core.PushFailureException }抛出.
   * 并且，<b>此时无需将产生错误的数据写入{@code output}</b>，系统会自动捕获
   * @param entry
   * @param output
   * @return
   * @throws PushFailureException
   */
  protected def getTask(entry: PushEntry, output: Output): Runnable = new Runnable {
    def run(): Unit = {

    }
  }
}
