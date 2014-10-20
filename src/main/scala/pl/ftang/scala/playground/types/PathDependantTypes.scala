package pl.ftang.scala.playground.types

object PathDependantTypes extends App {

  class Outer {
    class Inner
  }

  val out1 = new Outer
  val out1in = new out1.Inner // concrete instance, created from inside of Outer

  val out2 = new Outer
  val out2in = new out2.Inner // another instance of Inner, with the enclosing instance out2

  // the path dependent type. The "path" is "inside out1".
  type PathDep1 = out1.Inner


  // type checks

  val typeChecksOk: PathDep1 = out1in
  // OK

  // val typeCheckFails: PathDep1 = out2in // <- does not compile

  // <console>:27: error: type mismatch;
  // found   : out2.Inner
  // required: PathDep1
  //    (which expands to)  out1.Inner
  //       val typeCheckFails: PathDep1 = out2in
}

class Parent {
  class Child
}

/*
class ChildrenContainer(p: Parent) {
  type ChildOfThisParent = p.Child

  def add(c: ChildOfThisParent) = ???   // accept only children from this parent, not other
}*/
