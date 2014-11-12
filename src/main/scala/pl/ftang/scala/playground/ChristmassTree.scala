package pl.ftang.scala.playground

import annotation.tailrec

abstract class Tree(val left: Tree) {
  def o = new Ball(this)
  def x = new Spike(this)
  def * = new Candle(this)
  def oxo = new BigBall(this)
  def oo = new DoubleBall(this)
  def *** = new ElectricCandle(this)

  def / = new LeftNeedle(this)
  def \ = new RightNeedle(this)
  def | = new Trunk(this)

}
class Top(star: Star) extends Tree(star)
abstract class Needle(left: Tree) extends Tree(left)
class LeftNeedle(left: Tree) extends Needle(left)
class RightNeedle(left: Tree) extends Needle(left) {

  def |||() {
    |||(true)
  }

  @tailrec
  final def |||(sparkle: Boolean) {
    val f = (t: Tree) ⇒
    t match {
      case _: LeftNeedle => "/"
      case _: RightNeedle => "\\"
      case _: Trunk => "|"
      case _: Ball => "o"
      case _: Spike => "x"
      case _: Candle => "*"
      case _: BigBall => "oxo"
      case _: DoubleBall => "oo"
      case _: ElectricCandle => "***"
    }

    def walk(t: Tree, depth: Int): List[String] = {
      def walkLevel(t: Tree, acc: String,
                    f: (Tree) => String): (Tree, String) = {
        val fx = (t: Tree) => if (sparkle) f(t).toUpperCase else f(t)
        t match {
          case l: LeftNeedle => (l.left, fx(l) + "." + acc)
          case t: Tree => walkLevel(t.left, fx(t) + "." + acc, f)
        }
      }

      t match {
        case r: RightNeedle =>
      val l = walkLevel(r, "", f)
      l._2 +: walk(l._1, depth + 1)
        case s: Star =>
      List("-->*<-- ", "\\-/.")
      }
    }

    val tree = "||| " +: walk(this, 0)

    tree.reverse.foreach({l =>
      val numSpaces = 30 - (l.length() / 2)
      val padding = " " * numSpaces
      print(padding)
      println(l)
    })

    Thread.sleep(500)

    |||(!sparkle)
  }

}
class Trunk(parent: Tree) extends Tree(parent)

abstract class Decoration(left: Tree) extends Tree(left)
class Star extends Decoration(null)
class Candle(left: Tree) extends Decoration(left)
class Ball(left: Tree) extends Decoration(left)
class Spike(left: Tree) extends Decoration(left)
class BigBall(left: Tree) extends Decoration(left)
class DoubleBall(left: Tree) extends Decoration(left)
class ElectricCandle(left: Tree) extends Decoration(left)

trait DecorationBuilder {
  def \-/ = new PartialDecoration
}
class PartialDecoration {
  def -->*<-- = new Star
}

object ChristmasTree extends DecorationBuilder {

  def main(args: Array[String]) {
            \-/.
          -->*<--
             .
            /.\
          ./.|.\.
          /.oxo.\
        ./.*.|.x.\.
        /.oo.|.oo.\
      ./.oxo.|.***.\.
      /.*.oo.|.*.oo.\.
            |||
  }

}