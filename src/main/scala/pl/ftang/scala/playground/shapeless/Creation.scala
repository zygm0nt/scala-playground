package pl.ftang.scala.playground.shapeless

import Creator._

object Creation extends App {

  val amy = Creator.create[Person]("Amy,54.2")
  val hamlet = Creator.create[Book]("Hamlet,Shakespeare,1600")
  val finland = Creator.create[Country]("Finland,4500000,338424")

  println(s"$amy $hamlet $finland")
}
