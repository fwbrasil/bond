package net.fwbrasil

import shapeless._
import shapeless.tag._
import scala.language.experimental.macros

package object bond {

  implicit def narrowSymbol[S <: String](t: Symbol): Symbol @@ S = macro SingletonTypeMacros.narrowSymbol[S]
  
  implicit def toValidator0[T, M](validation: Validation0[T, M]) =
    new Validator[T, M](validation.isValid)

  implicit class toValidator1[T, M[_]](validation: Validation1[T, M]) {
    def apply[U <: T](w: Witness.Aux[U]) =
      new Validator[T, M[w.T]]((v: T) => validation.isValid(v, w.value))
  }
}
