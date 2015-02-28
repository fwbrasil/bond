package net.fwbrasil.bond.validation

import net.fwbrasil.bond._
import net.fwbrasil.bond.StartsWith
import shapeless.Witness.apply

object Test extends App {

    val v = StartsWith("bb").validate("bb").get
    val j = StartsWith("b").lift(v)

  val k = validate(Email)("a@a.com").get

  val r = validate(StartsWith("b"))("a@a.com")

}
 