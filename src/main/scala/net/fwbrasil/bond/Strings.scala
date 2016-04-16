package net.fwbrasil.bond

import scala.util.Try

trait Email
case object Email
  extends Validator[String, Email] {

  private val emailPattern =
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

  def isValid(v: String) =
    v.matches(emailPattern)
}

trait URL
case object URL
  extends Validator[String, URL] {

  def isValid(v: String) =
    Try { new java.net.URL(v) }.toOption.isDefined
}

trait StartsWith[-T]
case object StartsWith
  extends ParameterizedValidator[String, StartsWith] {

  def isValid(v: String, p: String) =
    v.startsWith(p)
}

trait EndsWith[-T]
case object EndsWith
  extends ParameterizedValidator[String, EndsWith] {

  def isValid(v: String, p: String) =
    v.endsWith(p)
}

trait MatchesRegex[-T]
case object MatchesRegex
  extends ParameterizedValidator[String, MatchesRegex] {

  def isValid(v: String, p: String) =
    v.matches(p)
}
