name := """play-scala-2.3"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"
lazy val akkaV = "2.3.11"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.akka" %% "akka-contrib" % akkaV,
  "com.typesafe.akka" %% "akka-cluster" % akkaV
)
