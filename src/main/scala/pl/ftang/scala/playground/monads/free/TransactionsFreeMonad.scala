package pl.ftang.scala.playground.monads.free

import java.sql.Connection

import cats.Functor
import cats.free.Free

/*
object TransactionsFreeMonad {
  type Sql[A] = Free[SqlOp,A]

  case class SqlOp[A](act: Connection => A)
  implicit object SqlOpFunctor extends Functor[SqlOp] {
    def map[A,B](a: SqlOp[A])(g: A => B) = SqlOp[B]((c:Connection) => g(a.act(c)))
  }

  def markAccountAsUpdated(account: Account, when: DateTime): Sql[Boolean] = Free.liftF(
    SqlOp((conn:java.sql.Connection) => {
      true
    })
  )

  def getAccountAndMarkAccess(personId: Long): Sql[Account] = for {
    person <- getPerson(personId)
    account <- getAccount(person.accountId)
    _ <- if (account.auditingIsEnabled) { markAccountAsUpdated(account, DateTime.now()) } else { None }
  } yield (account)
}
*/
