package net.fwbrasil.bond.typelevel

import language.experimental.macros
import scala.reflect.macros.whitebox.Context
import shapeless._, syntax.singleton._
import shapeless._
import syntax.singleton._

class Numbers {

}

object Number {
  

//  trait Nat {
//    type T
//    val value: Int
//  }
//
//  def apply: Int = macro apply_impl
//
//  def apply_impl(c: Context) = {
//    import c.universe._
//    val i = 10
//    val s = "_" + i
//    val members =
//      for (i <- 0 until 100) yield q"""
//      val $s = $i
//    """
////    q"""new { $members }"""
//    ???
//  }
}
