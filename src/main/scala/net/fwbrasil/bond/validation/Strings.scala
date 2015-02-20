package net.fwbrasil.bond.validation

import shapeless._, syntax.singleton._
import shapeless._
import syntax.singleton._
import net.fwbrasil.bond.Validator

trait Strings {

  trait Email
  object Email extends Validator[String, Email] {
    def valid(value: String) =
      value.matches(emailPattern)

    private def emailPattern =
      "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
  }

  //  trait StartsWith[S]
  //
  //  def startsWith[A <: String](s: Witness.Lt[A]): Validator[String, StartsWith[s.T]] =
  //    new Validator[String, StartsWith[s.T]] {
  //      def valid(value: String) =
  //        value.startsWith(s.value)
  //    }
  //  def startsWith2[A <: String](s: A)(implicit w: Witness.Lt[A]): Validator[String, StartsWith[w.T]] =
  //    new Validator[String, StartsWith[w.T]] {
  //      def valid(value: String) =
  //        value.startsWith(s)
  //    }
  //
  //  trait EndsWith
  //  trait MatchesRegex
  //  
  //  final val test = Witness("a")
  //  
  //  val a = startsWith(test)
  //  val c = startsWith2("b".narrow)

}
