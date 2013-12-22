package core.dto

import vos.PushPacket

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 12:00 PM
 *
 */
trait Bundle {
  //TODO 考虑此方法是否需要side effect
  def getPushPacket: PushPacket

  def getObject(key: String): Any

}

