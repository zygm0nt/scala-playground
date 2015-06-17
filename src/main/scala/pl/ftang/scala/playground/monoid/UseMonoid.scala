package pl.ftang.scala.playground.monoid

object UseMonoid {
  def howMany[A : Monoid](gm: A, gp: A): A = implicitly[Monoid[A]].append(gm, gp)
}
