package pl.ftang.scala.playground.scalaz.basics

import scalaz.Validation._
import scalaz._
import Scalaz._

object Validating extends App {

  val r = ("event 1 ok".success[String] |@| "event 2 failed!".failure[String] |@| "event 3 failed!".failure[String]) {_ + _ + _}
  println(r)

  println("event 1 ok".success[String])
  println("event 1 ok".successNel[String])

  println("event 1 failed!".failureNel[String])

  println(("event 1 ok".successNel[String] |@| "event 2 failed!".failureNel[String] |@| "event 3 failed!".failureNel[String]) {_ + _ + _})
}
