package net.fwbrasil

import net.fwbrasil.bond.validation._

package object bond
  extends Booleans
  with Iterables
  with Objects
  with Strings
  with Numbers {

  def validate[T1, V1, U](
    v1: Validator[T1, V1])(value: U)(
      implicit t1: U => T1): Result[U with V1] =
    v1.result[U](value)

  def validate[T1, T2, V1, V2, U](
    v1: Validator[T1, V1], v2: Validator[T2, V2])(value: U)(
      implicit t1: U => T1, t2: U => T2): Result[U with V1 with V2] =
    validate(v1)(value).flatMap(validate(v2))

  def validate[T1, T2, T3, V1, V2, V3, U](
    v1: Validator[T1, V1], v2: Validator[T2, V2], v3: Validator[T3, V3])(value: U)(
      implicit t1: U => T1, t2: U => T2, t3: U => T3): Result[U with V1 with V2 with V3] =
    validate(v1, v2)(value).flatMap(validate(v3))

  def validate[T1, T2, T3, T4, V1, V2, V3, V4, U](
    v1: Validator[T1, V1], v2: Validator[T2, V2], v3: Validator[T3, V3], v4: Validator[T4, V4])(value: U)(
      implicit t1: U => T1, t2: U => T2, t3: U => T3, t4: U => T4): Result[U with V1 with V2 with V3 with V4] =
    validate(v1, v2, v3)(value).flatMap(validate(v4))
    
  for {
    string <- validate(NonEmpty)("a")
    email <- validate(Email)(string)
  } yield {
    string
  }
}
