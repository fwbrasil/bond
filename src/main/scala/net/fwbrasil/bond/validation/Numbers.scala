package net.fwbrasil.bond.validation

import net.fwbrasil.bond.Validator
import shapeless._

trait Numbers {

  trait GreaterThanOrEqual
  trait LesserThan
  trait LesserThanOrEqual
  trait EquivalentTo
  trait Between

  trait GreaterThan[N <: Nat]
  def GreaterThan[N1 <: Nat, N2 <: Nat](nat: N2) =
    new Validator[N1, GreaterThan[N2]] {
      def valid[N <: N2](value: N) = true
    }

}