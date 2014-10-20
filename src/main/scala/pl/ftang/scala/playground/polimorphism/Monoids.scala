package pl.ftang.scala.playground.polimorphism

object Monoids extends App {
  def sum[M[_]: FoldLeft, A: Monoid](xs: M[A]): A = {
    val m = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }

  println(sum(List("a", "b", "c")))

  println(sum(List(1, 2, 3)))
}
