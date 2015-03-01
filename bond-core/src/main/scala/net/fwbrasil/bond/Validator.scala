package net.fwbrasil.bond

import shapeless.Witness
import language.experimental.macros

trait Validator[T, M] {

  def lift[U <: T](value: U): Any = macro Macros.lift[U, M]

  def validate[U <% T](value: U) =
    resultFor(value).asInstanceOf[Result[U with M]]

  def isValid(v: T): Boolean

  private def resultFor[U <% T](value: U) =
    if (isValid(value))
      Valid(value)
    else
      new Invalid(value, List(Violation(value, this)))
}

trait LiftableValidator[T, P, M[_]] {

  def apply[U <% P](w: Witness.Lt[U]) =
    new Validator[T, M[w.T]] {
      def isValid(v: T): Boolean = LiftableValidator.this.isValid(v, w.value)
      override def toString = LiftableValidator.this.toString + s"(${w.value})"
    }

  def lift(a: P, b: P): Boolean
  def isValid(v: T, p: P): Boolean
}

trait ParameterizedValidator[T, M[_]]
  extends LiftableValidator[T, T, M] {
  def lift(a: T, b: T) = isValid(a, b)
}

trait NumericValidator[M[_]]
  extends ParameterizedValidator[Number, M] {

  def isValid(v: Number, p: Number): Boolean =
    isValid[Double](v.doubleValue, p.doubleValue)

  def isValid[T](v: T, p: T)(implicit n: Numeric[T]): Boolean
}

