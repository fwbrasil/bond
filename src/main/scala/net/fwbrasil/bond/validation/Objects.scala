package net.fwbrasil.bond.validation

import net.fwbrasil.bond.Validator

trait Objects {

  trait IsNull
  object IsNull extends Validator[String, IsNull] {
    def valid(value: String) = value == null
  }

  trait NotNull
  object NotNull extends Validator[String, NotNull] {
    def valid(value: String) = value != null
  }
  
  trait Or
  trait And
}
