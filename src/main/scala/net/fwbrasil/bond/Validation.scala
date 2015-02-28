package net.fwbrasil.bond

trait Validation[T, M]

trait Validation0[T, M]
  extends Validation[T, M] {
  def isValid(v: T): Boolean
}

trait Validation1[T, M[_]]
  extends Validation[T, M[_]] {
  def isValid(v: T, p: T): Boolean
}