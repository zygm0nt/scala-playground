package pl.ftang.scala.playground.types

import scala.collection.mutable.ListBuffer

trait SimpleApp extends DelayedInit {

  private val initCode = new ListBuffer[() => Unit]

  override def delayedInit(body: => Unit) {
    initCode += (() => body)
  }

  def main(args: Array[String]) = {
    println("Whoa, I'm a SimpleApp!")

    for (proc <- initCode) proc()

    println("So long and thanks for all the fish!")
  }
}

// Running the bellow class would print print:
object Test extends SimpleApp { //
  // Whoa, I'm a SimpleApp!
  println("  Hello World!")     //   Hello World!
  // So long and thanks for all the fish!
}