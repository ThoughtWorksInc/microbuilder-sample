organization := "com.thoughtworks"

name := "rest-rpc-sample"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.6.4" % "test")

enablePlugins(RestRpc)