package net.fwbrasil.bond

trait NonEmpty
object NonEmpty extends Validator[Iterable[_], NonEmpty] {
  def isValid(v: Iterable[_]) =
    v.nonEmpty
}

trait Empty
object Empty extends Validator[Iterable[_], Empty] {
  def isValid(v: Iterable[_]) =
    v.isEmpty
}

trait Size[-T]
object Size extends LiftableValidator[Iterable[_], Int, Size] {
  def isValid(v: Iterable[_], p: Int) =
    v.size == p
  def lift(v: Int, p: Int) =
    v == p
}

trait MinSize[-T]
object MinSize extends LiftableValidator[Iterable[_], Int, MinSize] {
  def isValid(v: Iterable[_], p: Int) =
    v.size >= p
  def lift(a: Int, b: Int) =
    a >= b
}

trait MaxSize[-T]
object MaxSize extends LiftableValidator[Iterable[_], Int, MaxSize] {
  def isValid(v: Iterable[_], p: Int) =
    v.size <= p
  def lift(a: Int, b: Int) =
    a <= b
}
