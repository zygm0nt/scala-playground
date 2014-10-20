package pl.ftang.scala.playground.akka.eventbus

import java.io.{PrintStream, IOException}

object Logger {

  val log = (ps: PrintStream, msg: String) => {
    try {
      ps.println(msg)
    }
    catch {
      case ioe: IOException => println("IOException: " + ioe.toString)
      case e: IOException => println("Exception: " + e.toString)
    }
  }

  def stop(ps: PrintStream) = ps.close()
}