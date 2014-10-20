package pl.ftang.scala.playground.akka

import akka.actor.{Props, ActorSystem}
import akka.persistence.{EventsourcedProcessor, SnapshotOffer}

object PersistedPingPong extends App {

  case object Ball            //The Command
  case object BallReceived    //The Doamin Event, represents a fact, something that has happened
  case object Snap            //Command to request a snapshot to be made

  class PingPong extends EventsourcedProcessor {
    val name = self.path.name

    var counter = 0L

    def increment() = counter += 1 //state change function

    def receiveCommand = {
      case Ball =>
        increment()
        persist(BallReceived) {
          evt =>
            println(s"$name counter: $counter")
            if (counter % 10 == 0) self ! Snap     // Sends itself a message to take a snapshot every 10 Balls
            sender() ! Ball
            Thread.sleep(1000)                    //You should *never* call Thread.sleep in a real appliation. Just here to slow things down.
        }
      case Snap =>
        println(s"$name save Snapshot")
        saveSnapshot(counter)
    }

    def receiveRecover = {
      case BallReceived =>
        increment()
        println(s"$name counter: $counter (recover)")
      case SnapshotOffer(metadata, snapshot: Long) =>
        counter = snapshot
        println(s"$name metadata: $metadata")
        println(s"$name counter: $counter (snapshot)")
    }

  }

  val system = ActorSystem("PersistedPingPong")

  val ping = system.actorOf(Props(classOf[PingPong]), "ping")
  val pong = system.actorOf(Props(classOf[PingPong]), "pong")

  ping.tell(Ball, pong)

}