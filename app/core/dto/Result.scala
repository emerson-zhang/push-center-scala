package core.dto

import scala.collection.mutable.ListBuffer
import vos.PushRequest

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/22/13
 * Time: 9:30 PM
 *
 */
class Result(val jobId: String) {
  private val nodeResults: ListBuffer[NodeResult] = ListBuffer.empty

  def hasError: Boolean = nodeResults.filter(_ hasError).size != 0
}

object Result {
  def job(jobId: String): Result = new Result(jobId)

}


class NodeResult {
  private val errors: ListBuffer[ErrorEntry] = ListBuffer.empty

  /**
   *
   * @return
   */
  def hasError: Boolean = errors.length != 0

  /**
   *
   * @return
   */
  def errorList: List[ErrorEntry] = errors.toList


  def canEqual(other: Any): Boolean = other.isInstanceOf[NodeResult]


}

object NodeResult {
  def empty(): NodeResult = new NodeResult

  def error(cause: Throwable, entry: List[PushRequest]): NodeResult = {
    val result = empty()
    result.errors += ErrorEntry(cause, entry)
    result
  }


}

case class ErrorEntry(cause: Throwable, request: List[PushRequest])