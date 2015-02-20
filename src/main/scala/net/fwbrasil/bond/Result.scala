package net.fwbrasil.bond

sealed trait Result[+T] {

  def get: T

  def getOrElse[U >: T](default: => U): U

  def map[U](f: T => U) = flatMap(v => Valid(f(v)))

  def flatMap[U](f: T => Result[U]): Result[U]
}

case class Valid[T](value: T) extends Result[T] {

  def get = value

  def getOrElse[U >: T](default: => U) = value

  def flatMap[U](f: T => Result[U]) = f(value)
}

case class Invalid[T](value: T, violations: List[String]) extends Result[T] {

  case class InvalidValueException(violations: List[String]) extends Exception

  def get = throw InvalidValueException(violations)

  def getOrElse[U >: T](default: => U) = default

  def flatMap[U](f: T => Result[U]) =
    f(value) match {
      case valid: Valid[U]     => Invalid(valid.value, violations)
      case invalid: Invalid[U] => Invalid(invalid.value, invalid.violations ++ violations)
    }
}
