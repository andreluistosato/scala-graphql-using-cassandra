name := "Graphql with Cassanda"
version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.sangria-graphql" %% "sangria" % "1.3.0",
  "org.sangria-graphql" %% "sangria-spray-json" % "1.0.0",
  "com.typesafe.akka" %% "akka-http" % "10.0.9",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.9",
  "com.lightbend.akka" %% "akka-stream-alpakka-cassandra" % "0.13",
  "com.lightbend.akka" %% "akka-stream-alpakka-csv" % "0.13",
  "org.scalatest" %% "scalatest" % "3.0.4" % Test
)

Revolver.settings
enablePlugins(JavaAppPackaging)
