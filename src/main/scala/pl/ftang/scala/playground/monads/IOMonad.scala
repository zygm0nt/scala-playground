package pl.ftang.scala.playground.monads

// The state of the world changes between IO functions.
object RTConsole_v1 {
  def getString(state: WorldState) =
    (state.nextState, Console.readLine)
  def putString(state: WorldState, s: String) =
    (state.nextState, Console.print(s) )
}

// The world's state is what it is. You can't just create new ones
// whenever you want (val coolWorldState = new WorldState(){def jamesIsBillionaire = true}).
trait WorldState{def nextState:WorldState}

abstract class IOApplication_v1 {
  private class WorldStateImpl(id:BigInt)
    extends WorldState {
    def nextState = new WorldStateImpl(id + 1)
  }
  final def main(args:Array[String]):Unit = {
    iomain(args, new WorldStateImpl(0))
  }
  def iomain(
              args:Array[String],
              startState:WorldState):(WorldState, _)
}

object IOMonad extends IOApplication_v1 {
  import RTConsole_v1._
  def iomain(
              args:Array[String],
              startState:WorldState) =
    putString(startState, "Hello world")
}

// this fails
// NOT "The world is in exactly one state at any moment in time"
class Evil_v1 extends IOApplication_v1 {
  import RTConsole_v1._
  def iomain(
              args:Array[String],
              startState:WorldState) = {
    val (stateA, a) = getString(startState)
    val (stateB, b) = getString(startState)
    assert(a == b)
    (startState, b)
  }
}
