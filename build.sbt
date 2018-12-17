enablePlugins(ScalaJSPlugin)

enablePlugins(ScalaJSBundlerPlugin)

// @TODO[Security] Is this a good idea to leave this here long term?
// resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.raquo" %%% "airstream" % "0.4.1",
  "com.raquo" %%% "dombuilder" % "0.9",
  "com.raquo" %%% "domtypes" % "0.9.1", // Remove once dombuilder is updated
  "com.raquo" %%% "domtestutils" % "0.9" % Test,
  "org.scalatest" %%% "scalatest" % "3.0.5" % Test
)

useYarn := true

requiresDOM in Test := true

scalaJSUseMainModuleInitializer := true

emitSourceMaps := false
