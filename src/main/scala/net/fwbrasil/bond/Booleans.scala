package net.fwbrasil.bond

trait False
object False extends Validation0[Boolean, False] {
  def isValid(v: Boolean) =
    v == false
}

trait True
object True extends Validation0[Boolean, True] {
  def isValid(v: Boolean) =
    v == true
}