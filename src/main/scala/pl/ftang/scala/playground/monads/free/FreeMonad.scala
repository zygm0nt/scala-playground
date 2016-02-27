package pl.ftang.scala.playground.monads.free

import shapeless.PolyDefns.~>

import scala.concurrent.Future

case class Tickets(id: Long, ticketIds: Seq[String], count: Int)

sealed trait External[A]
case class InvokeTicketingService(count: Int) extends External[Tickets]

trait Free[S[_], A]
case class Pure[S[_], A](value: A) extends Free[S, A]
case class FlatMap[S[_], A, B](p: Free[S, A], f: A => Free[S, B]) extends Free[S, B]
case class Suspend[S[_], A](s: S[A]) extends Free[S, A]

case class UserTicketsRequest(ticketCount: Int)
/*
object FreeMonad {
  def purchaseTickets(input: UserTicketsRequest): Free[External, Option[Tickets]] = {
    if (input.ticketCount > 0) {
      // creates a "Suspend" node
      Free.liftF(InvokeTicketingService(input.ticketCount)).map(Some(_))
    } else {
      Free.pure(None)
    }
  }

  def bonusTickets(purchased: Option[Tickets]): Free[External, Option[Tickets]] = {
    if (purchased.exists(_.count > 10)) {
      Free.liftF(InvokeTicketingService(1)).map(Some(_))
    } else {
      Free.pure(None)
    }
  }

  def formatResponse(purchased: Option[Tickets], bonus: Option[Tickets]): String = ???

  val logic: Free[External, String] = for {
    purchased <- purchaseTickets(input)
    bonus <- bonusTickets(purchased)
  } yield formatResponse(purchased, bonus)

  val externalToServiceInvoker = new (External ~> Future) {
    override def apply[A](e: External[A]): Future[A] = e match {
      case InvokeTicketingService(c) => serviceInvoker.run(s"/tkts?count=$c")
    }
  }

  val result: Future[String] = logic.foldMap(externalToServiceInvoker)
}*/
