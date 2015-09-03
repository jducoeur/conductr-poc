resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

// TBD: is this really different from the one above it?
resolvers += "typesafe-releases" at "http://repo.typesafe.com/typesafe/maven-releases"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.9")

// web plugins

addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.0.0")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "3.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-bundle" % "1.0.0")
addSbtPlugin("com.typesafe.conductr" % "sbt-conductr" % "1.0.0")
