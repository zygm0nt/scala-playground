package pl.ftang.scala.playground.akka.eventbus

import akka.util.Subclassification
import akka.actor.ActorRef
import akka.event.{EventBus, SubchannelClassification}

object SCEventBus extends EventBus with SubchannelClassification {
  type Event = (String, Any, ActorRef)
  type Classifier = String
  type Subscriber = ActorRef

  override protected def classify(event: Event): Classifier = event._1

  protected def subclassification = new Subclassification[Classifier] {
    def isEqual(x: Classifier, y: Classifier) = x == y
    def isSubclass(x: Classifier, y: Classifier) = x.startsWith(y)
  }

  override protected def publish(event: Event, subscriber: Subscriber): Unit =
    subscriber.tell(event._2, event._3)
}