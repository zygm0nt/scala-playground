package pl.ftang.scala.playground.polimorphism

object Monoid {
  implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }
  implicit val StringMonoid: Monoid[String] = new Monoid[String] {
    def mappend(a: String, b: String): String = a + b
    def mzero: String = ""
  }

  implicit val ListMonoid: Monoid[List[Int]] = new Monoid[List[Int]] {
    def mappend(a: List[Int], b: List[Int]): List[Int] = a ++ b
    def mzero: List[Int] = List.empty
  }
}

trait Monoid[A] {
  def mappend(a1: A, a2: A): A
  def mzero: A
}

