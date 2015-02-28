package net.fwbrasil.bond

import scala.language.experimental.macros
import net.fwbrasil.bond.typelevel.Macros

trait Validation[T, M]

trait Validation0[T, M]
  extends Validation[T, M] {
  def isValid(v: T): Boolean
}

trait Validation1[T, M[_]]
  extends Validation[T, M[_]] {
  def isValid(v: T, p: T): Boolean
}

class Validator[T, M](f: T => Boolean) {
  def lift[U <: T](value: U): U with M = macro Macros.lift[U, M]

  private[bond] def validate[U <% T](value: U) =
    resultFor(value).asInstanceOf[Result[U with M]]

  private def resultFor[U <% T](value: U) =
    if (f(value))
      Valid(value)
    else
      new Invalid(value, List(Violation[T](value, this)))
}
