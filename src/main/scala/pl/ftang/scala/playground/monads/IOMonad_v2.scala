package pl.ftang.scala.playground.monads

class IOMonad_v2 {

}

object RTConsole_v2 {
  def getString = {state:WorldState =>
    (state.nextState, Console.readLine)}
  def putString(s: String) = {state: WorldState =>
    (state.nextState, Console.print(s))}
}


abstract class IOApplication_v2 {
  private class WorldStateImpl(id:BigInt)
    extends WorldState {
    def nextState = new WorldStateImpl(id + 1)
  }
  final def main(args:Array[String]):Unit = {
    val ioAction = iomain(args)
    ioAction(new WorldStateImpl(0))
  }
  def iomain(args:Array[String]):
  WorldState => (WorldState, _)
}

object IOMonad_v2 extends IOApplication_v2 {
  import RTConsole_v2._
  def iomain(args:Array[String]) =
    putString("Hello world")
}

// this fails!
// NOT "The world is in exactly one state at any moment in time"
class Evil_v2 extends IOApplication_v2 {
  import RTConsole_v2._
  def iomain(args:Array[String]) = {
    {startState:WorldState =>
      val (statea, a) = getString(startState)
      val (stateb, b) = getString(startState)
      assert(a == b)
      (startState, b)
    }
  }
}

