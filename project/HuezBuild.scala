import sbt._
import sbt.Keys._

object HuezBuild extends Build {

  lazy val huez = Project(
    id = "huez",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "huez",
      organization := "net.mcarolan",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.11.5",
      libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
    )
  )
}
