package net.fwbrasil.bond

trait IsEqualsTo[-T]
object IsEqualsTo extends ParameterizedValidator[Any, IsEqualsTo] {
  def isValid(v: Any, p: Any) =
    v == p
}

trait IsNull
object IsNull extends Validator[Any, IsNull] {
  def isValid(v: Any) =
    v == null
}

trait IsNotNull
object IsNotNull extends Validator[Any, IsNotNull] {
  def isValid(v: Any) =
    v != null
}
