package net.fwbrasil.bond

import shapeless._
import syntax.singleton._
import net.fwbrasil.bond._

object Test extends App {

  val v: String with StartsWith[T.`"bb"`.T] = StartsWith("bb")("bb").get
  val j: String with StartsWith[T.`"b"`.T] = StartsWith("b").lift(v)
  
//  val j = StartsWith("b").lift(v)
  
  val l1 = List(1, 2)
  
  val l2: List[Int] with MinSize[T.`2`.T] = MinSize(2)(l1).get
  val l3: List[Int] with MinSize[T.`1`.T] = MinSize(3).lift(l2)
//
//  val k = Email("a@a.com").get

}
