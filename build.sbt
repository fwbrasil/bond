name := "bond"

organization := "net.fwbrasil"

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4", "2.11.5")

libraryDependencies += "org.specs2" %% "specs2" % "2.4.2" % "test"

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"

releaseSettings

ReleaseKeys.crossBuild := true
ReleaseKeys.publishArtifactsAction := PgpKeys.publishSigned.value

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>http://github.com/fwbrasil/bond</url>
  <licenses>
    <license>
      <name>LGPL</name>
      <url>https://raw.githubusercontent.com/fwbrasil/bond/master/LICENSE-LGPL.txt</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:fwbrasil/bond.git</url>
    <connection>scm:git:git@github.com:fwbrasil/bond.git</connection>
  </scm>
  <developers>
    <developer>
      <id>fwbrasil</id>
      <name>Flavio W. Brasil</name>
      <url>http://github.com/fwbrasil/</url>
    </developer>
  </developers>)