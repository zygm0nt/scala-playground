package pl.ftang.scala.playground

import scala.concurrent.{ExecutionContext, Future, future}


object OperationsOnSeq {

  implicit val ctx = ExecutionContext.global
  def main(args: Array[String]) = {
    subtract
  }

  def subtract = {
    val a: Future[Seq[String]] = future { Seq("a", "b", "c")}
    val b: Future[Seq[String]] = future { Seq("a")}

    Future.sequence(Seq(a, b)).map { b =>
      b(0).diff(b(1))
    }
  }
}
