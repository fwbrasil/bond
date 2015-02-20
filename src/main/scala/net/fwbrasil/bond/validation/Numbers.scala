package net.fwbrasil.bond.validation

import shapeless._
import nat._
import ops.nat._
import net.fwbrasil.bond.Validator

trait Numbers {
  this: Validator =>

  trait GreaterThan[N <: Nat]
  def GreaterThan[N <: Nat] = new {
    def apply[T <: Int](value: T)(implicit ev: ToInt[N]) =
      validate[GreaterThan[N]](value)(value > toInt[N])
  }

  trait GreaterThanOrEqual[N <: Nat]
  def GreaterThanOrEqual[N <: Nat] = new {
    def apply[T <: Int](value: T)(implicit ev: ToInt[N]) =
      validate[GreaterThanOrEqual[N]](value)(value >= toInt[N])
  }

  trait LesserThan[N <: Nat]
  def LesserThan[N <: Nat] = new {
    def apply[T <: Int](value: T)(implicit ev: ToInt[N]) =
      validate[LesserThan[N]](value)(value < toInt[N])
  }

  trait LesserThanOrEqual[N <: Nat]
  def LesserThanOrEqual[N <: Nat] = new {
    def apply[T <: Int](value: T)(implicit ev: ToInt[N]) =
      validate[LesserThanOrEqual[N]](value)(value <= toInt[N])
  }

  trait EqualTo[N <: Nat]
  def EqualTo[N <: Nat] = new {
    def apply[T <: Int](value: T)(implicit ev: ToInt[N]) =
      validate[EqualTo[N]](value)(value == toInt[N])
  }

  trait Between[N1 <: Nat, N2 <: Nat]
  def Between[N1 <: Nat, N2 <: Nat] = new {
    def apply[T <: Int](value: T)(implicit ev1: ToInt[N1], ev2: ToInt[N2]) =
      validate[Between[N1, N2]](value)(value > toInt[N1] && value < toInt[N2])
  }
}

