package net.fwbrasil.bond

trait Email
object Email extends Validation0[String, Email] {
  private val emailPattern = """(\w+)@([\w\.]+)"""
  def isValid(v: String) =
    v.matches(emailPattern)
}

trait StartsWith[-T]
object StartsWith extends Validation1[String, StartsWith] {
  def isValid(v: String, p: String) =
    v.startsWith(p)
}

trait EndsWith[-T]
object EndsWith extends Validation1[String, EndsWith] {
  def isValid(v: String, p: String) =
    v.endsWith(p)
}

trait MatchesRegex[-T]
object MatchesRegex extends Validation1[String, MatchesRegex] {
  def isValid(v: String, p: String) =
    v.matches(p)
}
