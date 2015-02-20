package net.fwbrasil.bond

object Bond1 extends App {

  sealed trait Result[+T] {
    def map[U](f: T => U) = flatMap(v => Valid(f(v)))
    def flatMap[U](f: T => Result[U]): Result[U]
  }

  case class Valid[T](value: T) extends Result[T] {
    def flatMap[U](f: T => Result[U]): Result[U] =
      f(value)
  }
  case class Invalid[T](value: T, violations: List[String]) extends Result[T] {
    def flatMap[U](f: T => Result[U]): Result[U] =
      f(value) match {
        case valid: Valid[U]     => Invalid(valid.value, violations)
        case invalid: Invalid[U] => Invalid(invalid.value, invalid.violations ++ violations)
      }
  }

  trait Validation[T]
  trait Validator[T, V <: Validation[T]] {
    def result[U <% T](value: U): Result[U with V] =
      if (valid(value))
        Valid(value.asInstanceOf[U with V])
      else
        Invalid(value.asInstanceOf[U with V], List(violation(value)))
    def valid(value: T): Boolean
    private def violation(value: T) =
      s"Invalid $description: $value."
    protected def description =
      this.getClass.getSimpleName.split('$').last
  }

  trait Email extends Validation[String] {
    def test = "test"
  }
  object Email extends Validator[String, Email] {
    def valid(value: String) = value.contains("@")
  }

  trait NonEmpty extends Validation[Iterable[_]]
  object NonEmpty extends Validator[Iterable[_], NonEmpty] {
    def valid(value: Iterable[_]) = value.nonEmpty
  }

  trait Capital extends Validation[String]
  object Capital extends Validator[String, Capital] {
    def valid(value: String) = value.capitalize == value
  }

  def validate[T1, V1 <: Validation[T1], U](
    v1: Validator[T1, V1])(value: U)(
      implicit t1: U => T1): Result[U with V1] =
    v1.result[U](value)

  def validate[T1, T2, V1 <: Validation[T1], V2 <: Validation[T2], U](
    v1: Validator[T1, V1], v2: Validator[T2, V2])(value: U)(
      implicit t1: U => T1, t2: U => T2): Result[U with V1 with V2] =
    validate(v1)(value).flatMap(validate(v2))

  def validate[T1, T2, T3, V1 <: Validation[T1], V2 <: Validation[T2], V3 <: Validation[T3], U](
    v1: Validator[T1, V1], v2: Validator[T2, V2], v3: Validator[T3, V3])(value: U)(
      implicit t1: U => T1, t2: U => T2, t3: U => T3): Result[U with V1 with V2 with V3] =
    validate(v1, v2)(value).flatMap(validate(v3))

  def validate[T1, T2, T3, T4, V1 <: Validation[T1], V2 <: Validation[T2], V3 <: Validation[T3], V4 <: Validation[T4], U](
    v1: Validator[T1, V1], v2: Validator[T2, V2], v3: Validator[T3, V3], v4: Validator[T4, V4])(value: U)(
      implicit t1: U => T1, t2: U => T2, t3: U => T3, t4: U => T4): Result[U with V1 with V2 with V3 with V4] =
    validate(v1, v2, v3)(value).flatMap(validate(v4))

  val res =
    for {
      email <- validate(Email, NonEmpty)("aa")
      cap <- validate(Capital)(email)
    } yield {
      cap
    }

  println(res)
}

