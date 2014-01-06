package core.dto

import vos.PushEntry
import scala.collection.mutable.ListBuffer
import scala.collection.mutable

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 1/6/14
 * Time: 1:12 PM
 *
 */
class OutputImp extends Output {

  private val success = ListBuffer.empty[PushEntry]
  private val errors = mutable.HashMap.empty[PushEntry, Throwable]

  override def addErrorEntry(entry: PushEntry, cause: Throwable): Output = {
    errors.put(entry, cause)
    this
  }

  override def getErrors: Map[PushEntry, Throwable] = errors.toMap

  override def addSuccessEntry(entry: PushEntry): Output = {
    success += entry
    this
  }

  override def getSuccesses: List[PushEntry] = success.toList
}

object OutputImp {
  def apply() = {
    val output = new OutputImp
    output
  }
}
