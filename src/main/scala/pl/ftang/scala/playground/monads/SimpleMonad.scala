package pl.ftang.scala.playground.monads

class SimpleMonad[A] {

  def map[B](f: A => B): SimpleMonad[B] = ???
  def flatMap[B](f: A => SimpleMonad[B]): SimpleMonad[B] = ???
  //def filter(p: A => Boolean): SimpleMonad[A] = flatMap { x => if (p(x)) x else mzero}
}

