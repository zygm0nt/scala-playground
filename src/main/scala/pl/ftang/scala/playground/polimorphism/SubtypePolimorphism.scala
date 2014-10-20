package pl.ftang.scala.playground.polimorphism

class SubtypePolimorphism {

  def plus[A <: Plus1[A]](a1: A, a2: A): A = a1.plus(a2)

  def plus[A: Plus2](a1: A, a2: A): A = implicitly[Plus2[A]].plus(a1, a2)

  def sum(xs: List[Int]): Int = xs.foldLeft(0) { _ + _ }

  sum(List(1, 2, 3, 4))

  def sumMonoid1(xs: List[Int]): Int = xs.foldLeft(IntMonoid.mzero)(IntMonoid.mappend)
  def sumMonoid2(xs: List[Int], m: Monoid[Int]): Int = xs.foldLeft(m.mzero)(m.mappend)

  sumMonoid2(List(1, 2, 3, 4), IntMonoid)

  def sum[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)
  implicit val intMonoid = IntMonoid
  sum(List(1, 2, 3, 4))
}

object IntMonoid extends Monoid[Int] {
  def mappend(a: Int, b: Int): Int = a + b
  def mzero: Int = 0
}

trait Plus1[A] {
  def plus(a2: A): A
}

trait Plus2[A] {
  def plus(a1: A, a2: A): A
}