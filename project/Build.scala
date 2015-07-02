import sbt.Keys._
import sbt._

object Build extends Build {

  val scalazVersion = "7.0.6"
  val akkaVersion = "2.3.4"

  scalacOptions += "-feature"

  initialCommands in console := "import scalaz._, Scalaz._"

  resolvers := Seq(
    "Cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos"
  ) ++ resolvers.value

  lazy val root = Project(id = "root", base = file(".")).settings(
    name := "scala-playground",
    version := "1.0",
    scalaVersion := "2.11.5",
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2" % "2.3.13",
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
      "com.typesafe.akka" %% "akka-persistence-experimental" % akkaVersion,
      "org.apache.hive" % "hive-jdbc" % "0.12.0",
      "org.scalaz" %% "scalaz-core" % scalazVersion,
      "org.scalaz" %% "scalaz-effect" % scalazVersion,
      "org.scalaz" %% "scalaz-typelevel" % scalazVersion,
      "com.chuusai" % "shapeless_2.10.4" % "2.0.0",
      "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test",
      "org.scalacheck" %% "scalacheck" % "1.12.0" % "test"
    )

  /* scalacheck */
  )
}
