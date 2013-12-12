
package vos

import play.api.libs.json._
import play.api.libs.functional._

/**
 * Created with IntelliJ IDEA.
 * User: emerson
 * Date: 12/12/13
 * Time: 7:11 PM
 *
 */
case class Employee(age: Int, name: String, pet: Option[List[String]]) {
  override def toString: String = "age ->" + age + ", name ->" + name + ", ss :" + pet.mkString(" ")


}

object Employee {

  //  implicit val empFormat = Json.format[Employee]

  implicit object EmployeeReads extends Reads[Employee] {
    def reads(json: JsValue): JsResult[Employee] = (
      json \ "age").validate[Int].flatMap {
      age =>
        (json \ "name").validate[String].flatMap {
          name =>
            (json \ "pet").validate[Option[List[String]]].map { pet =>
              Employee(age,name,pet)

            }
        }
    }
  }

}


case class Pet(ani: String, name: String)


object Pet {
  implicit val petFormat = Json.format[Pet]
}

