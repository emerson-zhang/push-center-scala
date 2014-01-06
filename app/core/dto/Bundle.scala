package core.dto

import vos.PushPacket

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 12:00 PM
 *
 */
final case class Bundle(pushPacket:PushPacket, jobId:String,  output:Output) ;

