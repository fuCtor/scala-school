import _root_.sbt.Keys._

val commonSettings = Seq(
  version := "1.0",

  scalaVersion := "2.12.3",

  scalacOptions := Seq(
    "-encoding",
    "utf8",
    "-feature",
    "-unchecked",
    "-deprecation",
    "-target:jvm-1.8",
    //  "-Ymacro-debug-lite",
    "-language:_",
    "-Xexperimental"),

  libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)

val root = project.in(file(".")).settings(commonSettings)

val lecture1 = project.in(file("./lecture1")).settings(commonSettings)

val lecture2 = project.in(file("./lecture2")).settings(commonSettings)

val lecture3 = project.in(file("./lecture3")).settings(commonSettings)

val lecture4 = project.in(file("./lecture4")).settings(commonSettings)

val lecture5 = project.in(file("./lecture5")).settings(commonSettings)

val lecture6 = project.in(file("./lecture6")).settings(commonSettings)

val lecture7 = project.in(file("./lecture7")).settings(commonSettings)

val lecture8 = project.in(file("./lecture8")).settings(commonSettings)