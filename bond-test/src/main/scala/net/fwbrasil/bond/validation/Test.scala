package net.fwbrasil.bond

import shapeless._
import syntax.singleton._
import net.fwbrasil.bond._

object Test extends App {

  val v: String with StartsWith[T.`"bb"`.T] = StartsWith("bb")("bb").get
  val j: String with StartsWith[T.`"b"`.T] = StartsWith("c").lift(v)
  
//  val j = StartsWith("b").lift(v)

  val k = Email("a@a.com").get

}
