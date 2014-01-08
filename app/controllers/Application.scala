package controllers

import play.api.mvc._
import play.api.libs.json.Json

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is readerery."))
  }

  def helloPlay = Action {
    Ok(Json.obj("status" -> "OK"))
  }

}