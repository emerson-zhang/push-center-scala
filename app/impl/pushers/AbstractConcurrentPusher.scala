package impl.pushers

import core.{LoggerTrait, Pusher}
import java.util.concurrent.{TimeUnit, CountDownLatch, Executors, ExecutorService}
import vos.PushEntry
import core.dto.Output

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 1/8/14
 * Time: 3:00 PM
 *
 */
abstract class AbstractConcurrentPusher extends Pusher with LoggerTrait {


  /**
   * 用于管理线程
   */
  protected val EXECUTOR: ExecutorService = Executors.newCachedThreadPool

  /**
   * 对象销毁时清理
   */
  def cleanUp()

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
  protected def getTask(entry: PushEntry, output: Output): Runnable

  /**
   * 推送
   * @param list
   */
  def push(list: Vector[PushEntry], output: Output): Unit = {
    val logger = getLogger

    logger.debug(s"$tellMeYourPlatform pusher called")

    //使用闭锁来达到“所有推送线程都执行完毕，此方法才退出”的效果
    val endGate: CountDownLatch = new CountDownLatch(list.size)

    list.foreach {
      data => {
        try {
          //各子类实现如何创建一个推送任务
          val task = getTask(data, output)

          EXECUTOR.submit {
            try {
              //利用模板模式
              task.run()
            } finally {
              //计数器递减
              endGate.countDown()
            }
          }

        } catch {

          case ex: Throwable =>
            //创建任务的过程中可能会产生异常，捕获后记录
            logger.error("Create push thread error, " + ex)

            //计数器递减，否则方法直到超时才会退出
            endGate.countDown()

            //写入错误信息
            output.addErrorEntry(data, ex)
        }
      }
    }

    //等待该批次所有推送线程结束
    endGate.await(1, TimeUnit.DAYS)
    logger.info(tellMeYourPlatform + " pusher processes ended")
  }


  implicit def runToThread(f: => Unit): Thread = {
    new Thread() {
      override def run(): Unit = f
    }
  }


}
