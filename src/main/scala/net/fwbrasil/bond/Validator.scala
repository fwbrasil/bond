package net.fwbrasil.bond

trait Validator[T, V] {

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
