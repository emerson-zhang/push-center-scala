package core

import core.dto.Bundle
import org.slf4j.{LoggerFactory, Logger}

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 1/6/14
 * Time: 1:33 PM
 *
 */
class ProcessorChain(processors: List[Processor]) {



  def process(bundle: Bundle) {

    val logger = getLogger()

    processors.foreach {
      processor => {
        try {
          processor.process(bundle)
        } catch {
          //确保每个节点都被执行
          case ex: Throwable => logger.error(ex.toString)
        }
      }
    }
  }

  private def getLogger():Logger = LoggerFactory.getLogger(getClass)

}
