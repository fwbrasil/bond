package net.fwbrasil.bond

sealed trait Result[+T] {

  def get: T

  def map[U](f: T => U) = flatMap[U](f.andThen(Valid(_)))
  def flatMap[U](f: T => Result[U]): Result[U]
}

case class Valid[T](value: T) extends Result[T] {

  def get = value

  def flatMap[U](f: T => Result[U]) = f(value)
}

class Invalid[T](private val value: T, val violations: List[Violation[_]]) extends Result[T] {

  def get = throw ViolationsException(violations)

  def flatMap[U](f: T => Result[U]) =
    f(value) match {
      case valid: Valid[U]     => new Invalid(valid.value, violations)
      case invalid: Invalid[U] => new Invalid(invalid.value, invalid.violations ++ violations)
    }

  override def equals(that: Any) =
    that match {
      case that: Invalid[T] =>
        this.value == that.value &&
          this.violations == that.violations
      case _ => false
    }

  override def hashCode =
    value.hashCode + 31 * violations.hashCode
}

object Invalid {
  def unapply[T](invalid: Invalid[T]) =
    Some(invalid.violations)
}

case class Violation[T](value: T, validator: Validator[T, _])
case class ViolationsException(violations: List[Violation[_]]) extends Exception
