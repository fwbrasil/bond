package net.fwbrasil.bond.validation

import shapeless._
import syntax.singleton._
import shapeless._
import syntax.singleton._
import net.fwbrasil.bond._

class Test extends Strings {

  val v = StartsWith(Witness("a")).validate("b").get
  val j: String with StartsWith[Witness.`"b"`.T] =
    StartsWith.lift[String with StartsWith[Witness.`"b"`.T]](v)
  //    StartsWith.lift[String with StartsWith[Witness.`"a"`.T], Witness.`"b"`.T](v)

}