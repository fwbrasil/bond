package net.fwbrasil.bond

trait EqualsTo[-T]
case object EqualsTo
  extends ParameterizedValidator[Any, EqualsTo] {

  def isValid(v: Any, p: Any) =
    v == p
}

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