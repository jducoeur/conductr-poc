import ByteConversions._

name := """play-scala-2.3"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(JavaAppPackaging, PlayScala, ConductRPlugin)

scalaVersion := "2.11.6"
lazy val akkaV = "2.3.11"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.akka" %% "akka-contrib" % akkaV,
  "com.typesafe.akka" %% "akka-cluster" % akkaV,
  "com.typesafe.conductr" %% "play23-conductr-bundle-lib" % "1.0.1"
)

BundleKeys.nrOfCpus := 1.0
BundleKeys.memory := 64.MiB
BundleKeys.diskSpace := 5.MB
BundleKeys.startCommand += "-Dhttp.address=$WEB_BIND_IP -Dhttp.port=$WEB_BIND_PORT"
BundleKeys.system := "play-scala-poc"
BundleKeys.endpoints := Map(
  "akka-remote" -> Endpoint("tcp"),
  "web" -> Endpoint("http", services = Set(URI("http://:9000")))
)
