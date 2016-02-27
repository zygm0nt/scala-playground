package pl.ftang.scala.playground.typeclasses

/***
  * After
  * http://danielwestheide.com/blog/2013/02/06/the-neophytes-guide-to-scala-part-12-type-classes.html
  */


/**
  * Statistics for double
 */
object Statistics {
  def median(xs: Vector[Double]): Double = xs(xs.size / 2)
  def quartiles(xs: Vector[Double]): (Double, Double, Double) =
    (xs(xs.size / 4), median(xs), xs(xs.size / 4 * 3))
  def iqr(xs: Vector[Double]): Double = quartiles(xs) match {
    case (lowerQuartile, _, upperQuartile) => upperQuartile - lowerQuartile
  }
  def mean(xs: Vector[Double]): Double = {
    xs.reduce(_ + _) / xs.size
  }
}

/**
  * GoF implementation of Adapter pattern
  */
object StatisticsAsAdapters {
  trait NumberLike[A] {
    def get: A
    def plus(y: NumberLike[A]): NumberLike[A]
    def minus(y: NumberLike[A]): NumberLike[A]
    def divide(y: Int): NumberLike[A]
  }
  case class NumberLikeDouble(x: Double) extends NumberLike[Double] {
    def get: Double = x
    def minus(y: NumberLike[Double]) = NumberLikeDouble(x - y.get)
    def plus(y: NumberLike[Double]) = NumberLikeDouble(x + y.get)
    def divide(y: Int) = NumberLikeDouble(x / y)
  }
  type Quartile[A] = (NumberLike[A], NumberLike[A], NumberLike[A])
  def median[A](xs: Vector[NumberLike[A]]): NumberLike[A] = xs(xs.size / 2)
  def quartiles[A](xs: Vector[NumberLike[A]]): Quartile[A] =
    (xs(xs.size / 4), median(xs), xs(xs.size / 4 * 3))
  def iqr[A](xs: Vector[NumberLike[A]]): NumberLike[A] = quartiles(xs) match {
    case (lowerQuartile, _, upperQuartile) => upperQuartile.minus(lowerQuartile)
  }
  def mean[A](xs: Vector[NumberLike[A]]): NumberLike[A] =
    xs.reduce(_.plus(_)).divide(xs.size)
}

/**
  * Typeclass implementation
  *
  */
object Math {
  import annotation.implicitNotFound
  @implicitNotFound("No member of type class NumberLike in scope for ${T}")
  trait NumberLike[T] {
    def plus(x: T, y: T): T
    def divide(x: T, y: Int): T
    def minus(x: T, y: T): T
  }
  object NumberLike {
    implicit object NumberLikeDouble extends NumberLike[Double] {
      def plus(x: Double, y: Double): Double = x + y
      def divide(x: Double, y: Int): Double = x / y
      def minus(x: Double, y: Double): Double = x - y
    }
    implicit object NumberLikeInt extends NumberLike[Int] {
      def plus(x: Int, y: Int): Int = x + y
      def divide(x: Int, y: Int): Int = x / y
      def minus(x: Int, y: Int): Int = x - y
    }
  }
}

object StatisticsTypeclass {
  import Math.NumberLike
  def mean[T](xs: Vector[T])(implicit ev: NumberLike[T]): T =
    ev.divide(xs.reduce(ev.plus(_, _)), xs.size)

  def median[T : NumberLike](xs: Vector[T]): T = xs(xs.size / 2)
  def quartiles[T : NumberLike](xs: Vector[T]): (T, T, T) =
    (xs(xs.size / 4), median(xs), xs(xs.size / 4 * 3))
  def iqr[T : NumberLike](xs: Vector[T]): T = quartiles(xs) match {
    case (lowerQuartile, _, upperQuartile) =>
      implicitly[NumberLike[T]].minus(upperQuartile, lowerQuartile)
  }

  /**
    * A context bound T : NumberLike means that an implicit value of type NumberLike[T] must
    * be available, and so is really equivalent to having a second implicit parameter list with
    * a NumberLike[T] in it. If you want to access that implicitly available value, however,
    * you need to call the implicitly method, as we do in the iqr method. If your type class
    * requires more than one type parameter, you cannot use the context bound syntax.
    */
}

object TypeclassTest extends App {
  val numbers = Vector[Double](13, 23.0, 42, 45, 61, 73, 96, 100, 199, 420, 900, 3839)
  println(StatisticsTypeclass.mean(numbers))

  //println(StatisticsTypeclass.mean(Vector("a", "b")))
}