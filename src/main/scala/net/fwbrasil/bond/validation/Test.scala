package net.fwbrasil.bond.validation

import shapeless._
import syntax.singleton._
import shapeless._
import syntax.singleton._
import net.fwbrasil.bond._
import net.fwbrasil.bond.StartsWith

class Test {
  
  val v = StartsWith("bb")("bb").get
  val j = StartsWith("c").lift(v)

}
 