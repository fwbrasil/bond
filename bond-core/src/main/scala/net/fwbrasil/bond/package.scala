package net.fwbrasil

import shapeless._
import shapeless.tag._
import scala.language.experimental.macros

package object bond {
  type T = shapeless.Witness
  val T = shapeless.Witness
 
  implicit def narrowSymbol[S <: String](t: Symbol): Symbol @@ S = macro SingletonTypeMacros.narrowSymbol[S]
}