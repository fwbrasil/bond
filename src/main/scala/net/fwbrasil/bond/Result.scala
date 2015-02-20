package net.fwbrasil.bond

sealed trait Result[+T] {

  def get: T

  def map[U](f: T => U) = flatMap(v => Valid(f(v)))

  def flatMap[U](f: T => Result[U]): Result[U]
}

case class Valid[T](value: T) extends Result[T] {

  def get = value

  def flatMap[U](f: T => Result[U]) = f(value)
}

case class Invalid[T](value: T, violations: List[Violation[_]]) extends Result[T] {

  case class ViolationsException(violations: List[Violation[_]]) extends Exception

  def get = throw ViolationsException(violations)

  def flatMap[U](f: T => Result[U]) =
    f(value) match {
      case valid: Valid[U]     => Invalid(valid.value, violations)
      case invalid: Invalid[U] => Invalid(invalid.value, invalid.violations ++ violations)
    }
}

case class Violation[T](value: T, description: String)
