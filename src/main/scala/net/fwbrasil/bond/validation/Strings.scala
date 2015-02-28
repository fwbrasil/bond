package net.fwbrasil.bond.validation

import shapeless._
import syntax.singleton._
import shapeless._
import syntax.singleton._
import net.fwbrasil.bond._

trait Strings {

  private val emailPattern =
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

  trait Email
  object Email extends Validator0[String, Email](_.matches(emailPattern))

  trait StartsWith[-S]
  object StartsWith extends Validator1[String, StartsWith](_.startsWith(_))

  trait EndsWith
  trait MatchesRegex

  //  val a = StartsWith(Witness("aa"))("aa").get
  //
  //  def test(s: String with StartsWith[Witness.`"a"`.T]) = ???
  //
  //  test(StartsWith(a))

}