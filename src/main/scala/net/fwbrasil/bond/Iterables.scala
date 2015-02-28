package net.fwbrasil.bond

trait NonEmpty
object NonEmpty extends Validation0[Iterable[_], NonEmpty] {
  def isValid(v: Iterable[_]) =
    v.nonEmpty
}

trait Empty
object Empty extends Validation0[Iterable[_], Empty] {
  def isValid(v: Iterable[_]) =
    v.isEmpty
}


//  trait Size[N <: Nat]
//  def Size[N <: Nat] = new {
//    def apply[T <% Iterable[_]](value: T)(implicit ev: ToInt[N]) =
//      validate[Size[N]](value)(value.size == toInt[N])
//  }
