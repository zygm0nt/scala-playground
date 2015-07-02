package pl.ftang.scala.playground.monoid

case class CookieBox(count: Int)

trait Monoid[T] {
  def zero: T
  def append(t1: T, t2: T): T
}

object CookieBox {
  implicit val CookieBoxMonoid = new Monoid[CookieBox] {
    val zero = CookieBox(0)
    def append(i: CookieBox, j: CookieBox) = CookieBox(i.count + j.count)
  }

  def howMany[A : Monoid](gm: A, gp: A): A = implicitly[Monoid[A]].append(gm, gp)
  //def howMany[A : Monoid](gm: A, gp: A): A = gm append gp

  def main(args: Array[String]) = {
    println("combined cookie box: " + howMany(CookieBox(1), CookieBox(2)))
  }
}
