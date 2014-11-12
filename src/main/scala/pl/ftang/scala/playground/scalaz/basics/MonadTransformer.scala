package pl.ftang.scala.playground.scalaz.basics


import scalaz._
import Scalaz._

object MonadTransformer extends App {

  type Result[+A] = String \/ Option[A]

  val result: Result[Int] = some(42).right

  println(result)

  val transformed = result map { _ map { _.toString } }

  println(transformed)

  /* transformed version */
  type Error[+A] = \/[String, A]
  type Result2[+A] = OptionT[Error, A]

  val result2: Result2[Int] = 42.point[Result2]
  val transformed2 =
    for {
      value <- result
    } yield value.toString

  println(transformed2)
}
