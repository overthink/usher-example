name := "usher-example"
version := "0.1.0-SNAPSHOT"
organization := "com.markfeeney"
scalaVersion := "2.11.8"
scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings",
  "-Xlint",
  "-Ywarn-unused-import"
)
libraryDependencies ++= Seq(
  "com.markfeeney" % "usher_2.11" % "0.2.1"
)
