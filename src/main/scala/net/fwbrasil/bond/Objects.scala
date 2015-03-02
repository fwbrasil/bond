package net.fwbrasil.bond

trait IsNull
case object IsNull
  extends Validator[Any, IsNull] {

  def isValid(v: Any) =
    v == null
}

trait IsNotNull
case object IsNotNull
  extends Validator[Any, IsNotNull] {

  def isValid(v: Any) =
    v != null
}