package pl.ftang.scala.playground.scalaz.basics

import scalaz._
import Scalaz._

class Basics {

  // Equals
  1 === 1
  1.some =/= 2.some

  // Order
  1.0 ?|? 2.0
  1.0 max 2.0

  // Show
  3.show

  // Enum
  'a' |-> 'e'
  3 |=> 5
  //"b".succ
}
