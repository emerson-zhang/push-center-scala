package core.dto

import vos.PushEntry

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 1/6/14
 * Time: 10:20 AM
 *
 */
trait Output {
  def addErrorEntry(entry: PushEntry, cause: Throwable): Output

  def getErrors: Map[PushEntry, Throwable]

  def addSuccessEntry(entry: PushEntry): Output

  def getSuccesses: List[PushEntry]


}
