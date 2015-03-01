package net.fwbrasil.bond

trait GreaterThan[-T]
case object GreaterThan
  extends NumericValidator[GreaterThan] {

  def isValid[T](v: T, p: T)(implicit n: Numeric[T]) =
    n.gt(v, p)
}

trait GreaterThanOrEqual[-T]
case object GreaterThanOrEqual
  extends NumericValidator[GreaterThanOrEqual] {

  def isValid[T](v: T, p: T)(implicit n: Numeric[T]) =
    n.gteq(v, p)
}

trait LesserThan[-T]
case object LesserThan
  extends NumericValidator[LesserThan] {

  def isValid[T](v: T, p: T)(implicit n: Numeric[T]) =
    n.lt(v, p)
}

trait LesserThanOrEqual[-T]
case object LesserThanOrEqual
  extends NumericValidator[LesserThanOrEqual] {

  def isValid[T](v: T, p: T)(implicit n: Numeric[T]) =
    n.lteq(v, p)
}

trait Odd
case object Odd
  extends Validator[Int, Odd] {

  def isValid(v: Int) =
    v % 2 != 0
}

trait Even
case object Even
  extends Validator[Int, Even] {

  def isValid(v: Int) =
    v % 2 == 0
}

trait Prime
case object Prime
  extends Validator[Int, Prime] {

  def isValid(v: Int) =
    if (v <= 1)
      false
    else if (v == 2)
      true
    else
      !(2 to (v - 1)).exists(x => v % x == 0)
}

trait Perfect
case object Perfect
  extends Validator[Int, Perfect] {

  def isValid(v: Int) =
    v == (1 until v).foldLeft(0) { (acc, i) =>
      if (v % i == 0) acc + i else acc
    }
}