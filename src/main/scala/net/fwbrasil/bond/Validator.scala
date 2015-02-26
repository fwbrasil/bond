package net.fwbrasil.bond

import scala.language.experimental.macros
import net.fwbrasil.bond.typelevel.Macros
import shapeless._
import syntax.singleton._
import shapeless._
import syntax.singleton._

trait Validator[+T]

class Validator0[T, V](f: T => Boolean) extends Validator[T] {

  def validate[U <% T](value: U) =
    resultFor(value).asInstanceOf[Result[U with V]]

  def resultFor[U <% T](value: U) =
    if (f(value))
      Valid(value)
    else
      new Invalid(value, List(Violation(value, this)))
}

class Validator1[T, V[_]](f: (T, T) => Boolean) extends Validator[T] {

  def apply[U <: T](w: Witness.Aux[U]) =
    new Validator0[T, V[w.T]]((v: T) => f(w.value, v))

  implicit def lift[O](value: V[_]): O = macro Macros.lift[V, O]
}
