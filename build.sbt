name := "Reactive tweets"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.7",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.7",
  "com.typesafe.slick" %% "slick" % "3.2.0",
  "com.h2database" % "h2" % "1.4.193",
  "ch.qos.logback" % "logback-classic" % "1.1.7"
)
