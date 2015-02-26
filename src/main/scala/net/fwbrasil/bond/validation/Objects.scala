//package net.fwbrasil.bond.validation
//
//import net.fwbrasil.bond.Validator
//
//trait Objects {
//  this: Validator =>
//
//  trait IsNull
//  def IsNull[U <% Any](value: U) =
//    validate[IsNull](value)(value == null)
//
//  trait NotNull
//  def NotNull[U <% Any](value: U) =
//    validate[NotNull](value)(value != null)
//
//  trait Or
//  trait And
//}
