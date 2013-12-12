package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.{JsValue, Json}
import vos.Employee
import play.api.libs.iteratee.Enumeratee

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 7:06 PM
 *
 */
object TestController  extends Controller{

  def test = Action{


//    val empJson: JsValue = Json.parse("""{"age":21,"name":"Emerson"}""")
    val empJson: JsValue = Json.parse("""{"age":21,"name":"Emerson","pet":["ani","cat","name","RREsf"]}""")
//    val empJson: JsValue = Json.parse("""{"age":21,"name":"Emerson","pet":[{"ani":"cat","name":"RREsf"}]}""")


    val empObj = Json.fromJson[Employee](empJson)
    print(empObj.getClass)
    empObj.foreach(emp => println(emp))



    Ok(Json.obj("status" -> "Ok"))
  }

}

