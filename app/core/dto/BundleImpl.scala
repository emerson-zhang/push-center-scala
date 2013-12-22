package core.dto

import vos.PushPacket

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 12:09 PM
 *
 */
case class BundleImpl(packet: PushPacket, container: Map[String, Any]) extends Bundle {

  //TODO 复制对象
  def getPushPacket: PushPacket = packet

  def getObject(key: String): Any = container(key)
}
