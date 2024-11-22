// #Note this is /project/build.sbt – see /build.sbt for the main build config.

// Lets me depend on Maven Central artifacts immediately without waiting
resolvers ++= Resolver.sonatypeOssRepos("public")

lazy val root = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    buildInfoKeys := Seq[BuildInfoKey](
      BuildInfoKey.action("scalaDomTypesVersion")(ProjectVersions.ScalaDomTypes)
    ),
    buildInfoPackage := "metaProject",
    // Compile-time dependencies
    libraryDependencies ++= Seq(
      "com.raquo" %% "buildkit" % ProjectVersions.BuildKit,
      "com.raquo" %% "domtypes" % ProjectVersions.ScalaDomTypes,
    )
  )
