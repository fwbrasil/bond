import sbt._
import Keys._
import _root_.sbtrelease.ReleasePlugin._
import com.typesafe.sbt.pgp.PgpKeys

object BondBuild extends Build {


    lazy val root = 
        Project(
            id = "root",
            base = file("."),
            aggregate = Seq(macros, core, test),
            settings = commonSettings)

    lazy val macros =
        Project(
            id = "bond-macros",
            base = file("bond-macros"),
            settings = commonSettings ++ Seq(
                libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
                libraryDependencies += "net.fwbrasil" % "smirror_2.11" % "0.9"
            ))

    lazy val core =
        Project(
            id = "bond-core",
            base = file("bond-core"),
            dependencies = Seq(macros),
            settings = commonSettings ++ Seq(
                libraryDependencies += "com.chuusai" %% "shapeless" % "2.1.0",
                ReleaseKeys.publishArtifactsAction := PgpKeys.publishSigned.value,
                publishMavenStyle := true,
                publishTo := {
                  val nexus = "https://oss.sonatype.org/"
                  if (isSnapshot.value)
                    Some("snapshots" at nexus + "content/repositories/snapshots")
                  else
                    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
                },
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
            ))

    lazy val test =
        Project(
            id = "bond-test",
            base = file("bond-test"),
            dependencies = Seq(core),
            settings = commonSettings ++ Seq(
                libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
            ))

    def commonSettings =
        Defaults.defaultSettings ++ Seq(
            organization := "net.fwbrasil",
            scalaVersion := "2.11.5")
}