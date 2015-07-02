package pl.ftang.scala.playground.monads

sealed trait IOAction_v3[+A] extends
Function1[WorldState, (WorldState, A)]

object IOAction_v3 {
  def apply[A](expression: => A):IOAction_v3[A] =
    new SimpleAction(expression)

  private class SimpleAction [+A](
                                   expression: => A) extends IOAction_v3[A] {
    def apply(state:WorldState) =
      (state.nextState, expression)
  }
}

abstract class IOApplication_v3 {
  private class WorldStateImpl(id:BigInt)
    extends WorldState {
    def nextState = new WorldStateImpl(id + 1)
  }
  final def main(args:Array[String]):Unit = {
    val ioAction = iomain(args)
    ioAction(new WorldStateImpl(0))
  }
  def iomain(args:Array[String]):IOAction_v3[_]
}

object RTConsole_v3 {
  def getString = IOAction_v3(Console.readLine)
  def putString(s: String) =
    IOAction_v3(Console.print(s))
}

class HelloWorld_v3 extends IOApplication_v3 {
  import RTConsole_v3._
  def iomain(args:Array[String]) =
    putString("Hello world")
}
