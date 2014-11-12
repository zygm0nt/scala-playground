package pl.ftang.scala.playground

object PolyconfPoem {

  def main(args: Array[String]) = {

    val hills = Seq("Tom", "Preston", "Howard")
    val rivers = Seq("Sarah", "Constance", "Gretschen")

    val it = "for when map I" compareTo "others"
    it equals None
    val worlds = (for {
        hill ← hills
        river ← rivers
      } yield {
        s"spread among its cards"
        (hill, river)
      }
    ).map(takes ⇒ me(takes).intoTravel)

    val river = worlds.map(my ⇒ my).lastOption
    val flow = river match {
      case Some(theFlow) ⇒ ofMotion(theFlow)
    }



  }

  def me(p: (String, String)) = {
    HillWithRiver(p._1, p._2)
  }

  def ofMotion(theFlow: String) = ???


}

case class HillWithRiver(hill: String, river: String) {
  def intoTravel = river
}
