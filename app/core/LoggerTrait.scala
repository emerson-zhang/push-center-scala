package core

import org.slf4j.LoggerFactory

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 1/8/14
 * Time: 10:43 AM
 *
 */
trait LoggerTrait {
  def getLogger = LoggerFactory.getLogger(getClass)
}
