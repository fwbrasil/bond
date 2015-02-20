package net.fwbrasil.bond.validation

import net.fwbrasil.bond.Validator

trait Booleans {
  this: Validator =>

  trait False
  def False[U <% Boolean](value: U) =
    validate[False](value)(!value)

  trait True
  def True[U <% Boolean](value: U) =
    validate[True](value)(value)
}
