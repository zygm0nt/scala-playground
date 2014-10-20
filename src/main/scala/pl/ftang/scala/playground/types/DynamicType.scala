package pl.ftang.scala.playground.types


import scala.language.dynamics
import scala.util.parsing.json._

class Json(s: String) extends Dynamic {

  def applyDynamicNamed(name: String)(args: (String, Any)*) {
    println(s"""Creating a $name, for:\n "${args.head._1}": "${args.head._2}" """)
  }

  def selectDynamic(name: String): Option[String] = parse(s).get(name).asInstanceOf[Option[String]]

  private def parse(json: String) = {
    JSON.parseFull(json).get.asInstanceOf[Map[String, Any]]
  }
}

class DynamicType {
  val jsonString =
    """
    {
      "name": "Konrad",
      "favLangs": ["Scala", "Go", "SML"]
    }
  """


  val json = new Json(jsonString)

  val name: Option[String] = json.name

  println(s"Parsed: $name")
}

object DynamicTypeRunner extends App {
  new DynamicType
}


