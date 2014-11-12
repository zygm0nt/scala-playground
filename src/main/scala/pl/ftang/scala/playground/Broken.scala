package pl.ftang.scala.playground

object Broken {

  def main(args: Array[String]) = {

    println(poem("broken"))
  }

  def poem(adj: String) = {
    Seq("my computer",
      "my compiler",
      "my keyboard",
      "my keychain",
      "my application server",
      "my favourite editor",
      "all").map(i ⇒ s"$i is $adj").mkString("\n")


  }
}
