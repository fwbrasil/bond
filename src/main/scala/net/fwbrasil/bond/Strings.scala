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

trait URI
case object URI
  extends Validator[String, URI] {

  def isValid(v: String) =
    Try { new java.net.URI(v) }.toOption.isDefined
}

trait URL
case object URL
  extends Validator[String, URL] {

  def isValid(v: String) =
    Try { new java.net.URL(v) }.toOption.isDefined
}

trait CreditCard
case object CreditCard
  extends Validator[String, CreditCard] {

  def isValid(v: String) = {
    v.reverse.sliding(1).map(_.toInt).toList.zipWithIndex.map {
      case (v, k) if k % 2 == 0 => (v, k)
      case (v, k)               => (v * 2, k)
    }.unzip._1.map( i => {
      i match {
        case i if i >= 10 => i.toString.sliding(1).map(_.toInt).sum
        case i            => i
      }
    }).sum % 10 == 0
  }
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
