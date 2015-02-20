package net.fwbrasil.bond.validation

import net.fwbrasil.bond.Validator

trait Iterables {
  this: Validator =>

  trait NonEmpty
  def NonEmpty[U <% Iterable[_]](value: U) =
    validate[NonEmpty](value)(value.nonEmpty)

  trait Empty
  def Empty[U <% Iterable[_]](value: U) =
    validate[Empty](value)(value.isEmpty)
}
