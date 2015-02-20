package net.fwbrasil.bond.validation

import net.fwbrasil.bond.Validator

class Booleans {

  trait False
  object False extends Validator[Boolean, False] {
    def valid(value: Boolean) = !value
  }

  trait True
  object True extends Validator[Boolean, True] {
    def valid(value: Boolean) = value
  }
}