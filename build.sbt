enablePlugins(HaxeJavaPlugin)

enablePlugins(HaxeCSharpPlugin)

enablePlugins(HaxeCppPlugin)

enablePlugins(HaxeFlashPlugin)

enablePlugins(HaxeAs3Plugin)

enablePlugins(HaxePythonPlugin)

enablePlugins(HaxeNekoPlugin)

enablePlugins(HaxePhpPlugin)

enablePlugins(HaxeJsPlugin)

enablePlugins(RestRpc)

enablePlugins(PlayScala)

organization := "com.thoughtworks"

name := "rest-rpc-sample"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.6.4" % Test

routesGenerator := InjectedRoutesGenerator

for (c <- Seq(Compile, Test)) yield {
  haxeOptions in c ++= Seq("-lib", "continuation")
}

for (c <- Seq(Compile, Test)) yield {
  haxeOptions in c ++= Seq("-D", "scala")
}

for (c <- Seq(Compile, Test)) yield {
  haxeOptions in c ++= Seq("-dce", "no")
}

libraryDependencies += "com.qifun" %% "json-stream" % "0.2.3" % HaxeJava classifier "haxe-java"

libraryDependencies += "com.qifun" %% "json-stream" % "0.2.3"

libraryDependencies += "com.qifun" %% "haxe-scala-stm" % "0.1.4" % HaxeJava classifier "haxe-java"


for (c <- AllTargetConfigurations) yield {
  haxeMacros in c += """com.dongxiguo.autoParser.AutoParser.BUILDER.defineMacroClass([ "com.thoughtworks.restRpc.core.UriTemplate" ], "com.thoughtworks.restRpc.core.UriTemplateParser")"""
}

for (c <- AllTargetConfigurations) yield {
  haxeMacros in c += """com.dongxiguo.autoParser.AutoFormatter.BUILDER.defineMacroClass([ "com.thoughtworks.restRpc.core.UriTemplate" ], "com.thoughtworks.restRpc.core.UriTemplateFormatter")"""
}
