package net.fwbrasil.bond.validation

import shapeless._
import syntax.singleton._
import shapeless._
import syntax.singleton._
import net.fwbrasil.bond._

class Test extends Strings {
  
  val a: String with StartsWith[Witness.`"a"`.T] = ???
  val z: String with StartsWith[Witness.`"b"`.T] = a

  val v = StartsWith(Witness("a")).validate("b").get
  val j =
      StartsWith(Witness("b")).lift(v)

}
 