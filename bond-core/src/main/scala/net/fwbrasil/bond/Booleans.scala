package net.fwbrasil.bond

trait False
case object False extends Validator[Boolean, False] {
  def isValid(v: Boolean) =
    v == false
}

trait True
case object True extends Validator[Boolean, True] {
  def isValid(v: Boolean) =
    v == true
}