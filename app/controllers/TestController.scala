package controllers

import play.api.mvc._
import play.api.libs.json.Json

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 7:06 PM
 *
 */
object TestController extends Controller {

  def test = Action {
    Ok(Json.obj("status" -> "Ok"))
  }

}

