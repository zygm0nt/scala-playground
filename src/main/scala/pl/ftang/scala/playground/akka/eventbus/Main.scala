package pl.ftang.scala.playground.akka.eventbus

import java.io.{File, FileOutputStream, PrintStream}

object Main extends App{

  // set up logger
  val ps = new PrintStream(new FileOutputStream(new File("output.txt")))
  val log = Logger.log(ps, _: String)

  // create subscribers
  val rootSubscriber = Actors.create(
    classOf[Subscription], "rootSubscriber", Actors.onReceive)

  val eventSubscriber = Actors.create(
    classOf[Subscription], "eventSubscriber", Actors.onReceive)

  val itemSubscriber = Actors.create(
    classOf[Subscription], "itemSubscriber", Actors.onReceive(log))

  // set up subscriptions
  SCEventBus.subscribe(rootSubscriber, "/")
  SCEventBus.subscribe(eventSubscriber, "/event")
  SCEventBus.subscribe(itemSubscriber, "/event/42")

  // create event publisher
  val eventPublisher = Actors.create(
    classOf[Subscription], "eventPublisher", Actors.onReceive)

  // generate some events
  SCEventBus.publish(("/", "payload A", eventPublisher))
  SCEventBus.publish(("/event", "payload B", eventPublisher))
  SCEventBus.publish(("/event/42", "payload C", eventPublisher))

  // clean up
  Logger.stop(ps)
}