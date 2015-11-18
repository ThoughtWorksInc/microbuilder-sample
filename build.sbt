enablePlugins(Microbuilder)

enablePlugins(PlayScala)

organization := "com.thoughtworks"

name := "microbuilder-sample"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.6.4" % Test

routesGenerator := InjectedRoutesGenerator






