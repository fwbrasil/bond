package net.fwbrasil.bond

import scala.language.experimental.macros
import net.fwbrasil.bond.typelevel.Macros

class Validator[T, M](f: T => Boolean) {
  
  def lift[U <: T](value: U): U with M = macro Macros.lift[U, M]

  def apply[U <% T](value: U) =
    resultFor(value).asInstanceOf[Result[U with M]]

  private def resultFor[U <% T](value: U) =
    if (f(value))
      Valid(value)
    else
      new Invalid(value, List(Violation[T](value, this)))
}
