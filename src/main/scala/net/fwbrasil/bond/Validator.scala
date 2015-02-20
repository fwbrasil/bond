package net.fwbrasil.bond

trait Validator {

  protected def validate[V] = new {
    def apply[T](value: T)(valid: Boolean, description: String = s"invalid value: $value"): Result[T with V] =
      if (valid)
        Valid(cast[T, V](value))
      else
        Invalid(cast[T, V](value), List(Violation(cast[T, V](value), description)))
  }

  private def cast[T, V](value: T) =
    value.asInstanceOf[T with V]
}
