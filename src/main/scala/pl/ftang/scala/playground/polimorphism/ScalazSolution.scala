package pl.ftang.scala.playground.polimorphism

object ScalazSolution extends App{

  implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
    val F = implicitly[Monoid[A]]
    val value = a
  }

  println(3 |+| 4)

  println("a" |+| "b")

  println(List(1, 2, 3) |+| List(5,6,87))
}

trait MonoidOp[A] {
  val F: Monoid[A]
  val value: A
  def |+|(a2: A) = F.mappend(value, a2)
}