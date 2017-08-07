lazy val settings = Seq(
  organization := "de.ioswarm"
  , version := "0.1.0.SNAPSHOT"
  , scalaVersion := "2.12.1"
  , scalacOptions ++= Seq(
    "-feature",
    "-language:_",
    "-unchecked",
    "-deprecation",
    "-encoding", "utf8",
    "-target:jvm-1.8"
  )
)

lazy val elsa = project.in(file("."))
    .settings(settings)
    .settings(
      name := "elsa"
    )
    .aggregate(
      elsaAPI
      , elsaCore
    )

lazy val elsaAPI = project.in(file("elsa-api"))
    .settings(settings)
    .settings(
      name := "elsa-api"
      , libraryDependencies ++= Seq(
        "io.argonaut" %% "argonaut" % "6.2"
      )
    )

lazy val elsaCore = project.in(file("elsa-core"))
    .settings(settings)
    .settings(
      name := "elsa-core"
    )
    .dependsOn(
      elsaAPI
    )
