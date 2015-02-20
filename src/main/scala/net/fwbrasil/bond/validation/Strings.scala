package net.fwbrasil.bond.validation

import net.fwbrasil.bond.Validator

trait Strings {

  trait Email
  object Email extends Validator[String, Email] {
    def valid(value: String) =
      value.matches(emailPattern)

    private def emailPattern =
      "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
  }
}
