package net.fwbrasil.bond

trait Email
object Email extends Validator0[String, Email] {
  private val emailPattern = """(\w+)@([\w\.]+)"""
  def isValid(v: String) =
    v.matches(emailPattern)
}

trait StartsWith[-T]
object StartsWith extends Validator1[String, StartsWith] {
  def isValid(v: String, p: String) =
    v.startsWith(p)
}

trait EndsWith[-T]
object EndsWith extends Validator1[String, EndsWith] {
  def isValid(v: String, p: String) =
    v.endsWith(p)
}

trait MatchesRegex[-T]
object MatchesRegex extends Validator1[String, MatchesRegex] {
  def isValid(v: String, p: String) =
    v.matches(p)
}
