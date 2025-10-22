ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.7"

lazy val root = (project in file("."))
  .settings(
    name := "apache-pekko"
  )

libraryDependencies ++= Seq(
    "org.apache.pekko" %% "pekko-actor" % "1.2.1"
)