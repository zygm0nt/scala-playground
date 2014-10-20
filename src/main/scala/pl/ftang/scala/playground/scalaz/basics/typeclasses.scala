package pl.ftang.scala.playground.scalaz.basics

import scalaz._
import Scalaz._

sealed trait TrafficLight
case object Red extends TrafficLight
case object Yellow extends TrafficLight
case object Green extends TrafficLight

class Typeclasses {
  case class TrafficLight(name: String)
  val red = TrafficLight("red")
  val yellow = TrafficLight("yellow")
  val green = TrafficLight("green")

  implicit val TrafficLightEqual: Equal[TrafficLight] = Equal.equal(_ == _)

  // Red === Yellow // won't compile: Equal has nonvariant subtyping: Equal[F]

  red === yellow
}