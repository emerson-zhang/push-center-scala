package core

import core.dto.Bundle

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 12:11 PM
 *
 */
trait Processor {
  def process(bundle:Bundle):Option[Any]
}
