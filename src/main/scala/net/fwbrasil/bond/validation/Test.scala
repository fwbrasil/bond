package net.fwbrasil.bond.validation

import net.fwbrasil.bond._
import net.fwbrasil.bond.StartsWith
import shapeless.Witness.apply

object Test extends App {

  val v = StartsWith("bb")("bb").get
//  val j = StartsWith("b").lift(v)

  val k = Email("a@a.com").get

}
 