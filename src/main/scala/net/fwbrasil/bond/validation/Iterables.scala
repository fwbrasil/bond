package net.fwbrasil.bond.validation

import net.fwbrasil.bond.Validator

trait Iterables {

  trait NonEmpty
  object NonEmpty extends Validator[Iterable[_], NonEmpty] {
    def valid(value: Iterable[_]) = value.nonEmpty
  }

  trait Empty
  object Empty extends Validator[Iterable[_], Empty] {
    def valid(value: Iterable[_]) = value.isEmpty
  }

}