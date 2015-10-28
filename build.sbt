enablePlugins(HaxeJavaPlugin)

enablePlugins(HaxeCSharpPlugin)

enablePlugins(HaxeCppPlugin)

enablePlugins(HaxeFlashPlugin)

enablePlugins(HaxeAs3Plugin)

enablePlugins(HaxePythonPlugin)

enablePlugins(HaxeNekoPlugin)

enablePlugins(HaxePhpPlugin)

// Haxe compiler will hang up when compiling for JavaScript target
// enablePlugins(HaxeJsPlugin)

enablePlugins(Microbuilder)

enablePlugins(PlayScala)

organization := "com.thoughtworks"

name := "microbuilder-sample"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.6.4" % Test

routesGenerator := InjectedRoutesGenerator

for (c <- AllTargetConfigurations ++ AllTestTargetConfigurations) yield {
  haxeOptions in c ++= Seq(
    "-lib", "continuation",
    "-dce", "no")
}

for (c <- Seq(Compile, Test)) yield {
  haxeOptions in c ++= Seq("-D", "scala")
}

libraryDependencies += "com.thoughtworks.microbuilder" %% "json-stream" % "2.0.0"% HaxeJava classifier HaxeJava.name

libraryDependencies += "com.thoughtworks.microbuilder" %% "json-stream" % "2.0.0"

libraryDependencies += "com.qifun" %% "haxe-scala-stm" % "0.1.4" % HaxeJava classifier HaxeJava.name
