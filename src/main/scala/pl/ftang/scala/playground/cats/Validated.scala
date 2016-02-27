package pl.ftang.scala.playground.cats

import cats.data.{Validated, Xor}
import cats.syntax.cartesian._ // For |@| syntax
import cats.std.list._ // For semigroup (append) on List

object ValidatedExample extends App {
  import Xor.{left, right}
  import Validated.{invalid, valid}

  // We are going to compare the behaviour of Xor and Validated.  First we
  // define some instances. Then we combine them using flatMap
  // (for-comprehension) or `|@|` as appropriate.

  type Error = List[String]
  type XorR  = Xor[Error, Int]
  type ValidatedR = Validated[Error, Int]

  val x1: XorR = right(1)
  val x2: XorR = left(List("Stops here"))
  val x3: XorR = left(List("This will be ignored"))

  val v1: ValidatedR = valid(1)
  val v2: ValidatedR = invalid(List("Accumulates this"))
  val v3: ValidatedR = invalid(List("And this"))

  // Stops as soon as we encounter an error
  println(
    for {
      x <- x1
      y <- x2
      z <- x3
    } yield x + y + z
  )

  // Accumulates all the errors
  println(
    (v1 |@| v2 |@| v3) map { _ + _ + _ }
  )
}