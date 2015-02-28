//package net.fwbrasil.bond
//
//trait GreaterThan[-T]
//object GreaterThan extends Validation1[String, GreaterThan] {
//  def isValid(v: String, p: String) =
//    v > p
//}
//
//trait GreaterThanOrEqual[-T]
//object GreaterThanOrEqual extends Validation1[String, GreaterThanOrEqual] {
//  def isValid(v: String, p: String) =
//    v >= p
//}
//
//trait LesserThan[-T]
//object LesserThan extends Validation1[Numeric[_], LesserThan] {
//  def isValid(v: String, p: String) =
//    v < p
//}
//
//trait LesserThanOrEqual[-T]
//object LesserThanOrEqual extends Validation1[String, LesserThanOrEqual] {
//  def isValid(v: String, p: String) =
//    v <= p
//}