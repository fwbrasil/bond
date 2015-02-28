package net.fwbrasil.bond

import shapeless._

import language.experimental.macros

trait Validator[T, M] {

  def lift[U <: T](value: U): Any = macro Macros.lift[U, M]

  def apply[U <% T](value: U) =
    resultFor(value).asInstanceOf[Result[U with M]]

  def isValid(v: T): Boolean

  private def resultFor[U <% T](value: U) =
    if (isValid(value))
      Valid(value)
    else
      new Invalid(value, List())
}

trait ParameterizedValidator[T, M[_]] {

  def apply[U <: T](w: Witness.Lt[U]) =
    new Validator[T, M[w.T]] {
      def isValid(v: T): Boolean = ParameterizedValidator.this.isValid(v, w.value)
    }

  def isValid(v: T, p: T): Boolean
}
