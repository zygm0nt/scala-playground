package pl.ftang.scala.playground.concurrent

import org.specs2.mutable.Specification
import org.specs2.time.NoTimeConversions

import scala.concurrent._
import scala.concurrent.duration._

class ParallelFutures {

  implicit val ec = ExecutionContext.global

  def runFuture(delay: Integer, name: String) = future {
    Thread.sleep(delay * 1000)
    println(s"now running as $name")
    name
  }

  def pseudoParallelFutures(): Future[Unit] = {
    val combinedFuture = for {
      n1 <- runFuture(4, "fut1")
      n2 <- runFuture(1, "fut2")
      n3 <- runFuture(3, "fut3")
    } yield (n1, n2, n3)

    combinedFuture.map { case (a, b, c) =>
        println(s"Got: $a $b $c")
    }
  }

  def parallelFutures(): Future[Unit] = {
    val fut1 = runFuture(4, "fut1")
    val fut2 = runFuture(1, "fut2")
    val fut3 = runFuture(3, "fut3")
    val combinedFuture = for {
      n1 ← fut1
      n2 ← fut2
      n3 ← fut3
    } yield (n1, n2, n3)

    combinedFuture.map { case (a, b, c) =>
      println(s"Got: $a $b $c")
    }
  }

  def waitForOneFuture(): Future[Unit] = {
    val fut1 = runFuture(4, "fut1")
    val fut2 = runFuture(1, "fut2")
    val fut3 = runFuture(3, "fut3")
    val combinedFuture = for {
      n2 ← fut2
    } yield n2

    combinedFuture.map { case (a) =>
      println(s"Got: $a")
    }
  }
}


class ParallelFuturesSpec extends Specification with NoTimeConversions {

  "ParallelFutures" should {
    "execute futures in sequence" in {
      val result = new ParallelFutures().pseudoParallelFutures()
      result must not(throwA[Exception]).await(1, 5 seconds)
    }

    "execute futures in parallel" in {
      val result = new ParallelFutures().parallelFutures()
      result must throwA[Exception].await(1, 5 seconds)
    }

    "return just one result and don't wait for others" in {
      val result = new ParallelFutures().waitForOneFuture()
      Thread.sleep(5000)
      result must not(throwA[Exception]).await(1, 2 seconds)
    }
  }
}