package pl.ftang.scala.playground.types

import java.io.StringReader

/**
 * WARNING : uses reflection -> have performance penalty
 */
object StructuralTypes extends App {
  type JavaCloseable = java.io.Closeable
  // reminder, it's body is: { def close(): Unit }

  class MyOwnCloseable {
    def close(): Unit = ()
  }


  // method taking a Structural Type
  def closeQuietly(closeable: { def close(): Unit }) =
    try {
      closeable.close()
    } catch {
      case ex: Exception => // ignore...
    }


  // accepts a java.io.File (implements Closeable):
  closeQuietly(new StringReader("example"))

  // accepts a MyOwnCloseable
  closeQuietly(new MyOwnCloseable)
}
