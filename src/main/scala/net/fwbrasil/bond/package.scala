package net.fwbrasil

import shapeless._
import shapeless.syntax.singleton._
import shapeless._
import shapeless.syntax.singleton._
import net.fwbrasil.bond._

package object bond {
  
  implicit class Param1[T, M[_]](validation: Validation1[T, M]) {
    def apply[U <: T](w: Witness.Aux[U])  =
      new Validator[T, M[w.T]]((v: T) => validation.isValid(v, w.value))
  }

}
