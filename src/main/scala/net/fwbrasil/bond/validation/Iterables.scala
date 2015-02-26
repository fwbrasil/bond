//package net.fwbrasil.bond.validation
//
//import shapeless._
//import nat._
//import ops.nat._
//
//import net.fwbrasil.bond.Validator
//
//trait Iterables {
//  this: Validator =>
//
//  trait NonEmpty
//  def NonEmpty[U <% Iterable[_]](value: U) =
//    validate[NonEmpty](value)(value.nonEmpty)
//
//  trait Empty
//  def Empty[U <% Iterable[_]](value: U) =
//    validate[Empty](value)(value.isEmpty)
//
//  trait Size[N <: Nat]
//  def Size[N <: Nat] = new {
//    def apply[T <% Iterable[_]](value: T)(implicit ev: ToInt[N]) =
//      validate[Size[N]](value)(value.size == toInt[N])
//  }
//}
