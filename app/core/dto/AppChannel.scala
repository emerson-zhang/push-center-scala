package core.dto

import core.Pusher

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/21/13
 * Time: 9:16 PM
 *
 */
case class AppChannel(appKey: String, appSecret: String, pushers: List[Pusher])
