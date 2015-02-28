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


//  trait Size[N <: Nat]
//  def Size[N <: Nat] = new {
//    def apply[T <% Iterable[_]](value: T)(implicit ev: ToInt[N]) =
//      validate[Size[N]](value)(value.size == toInt[N])
//  }
