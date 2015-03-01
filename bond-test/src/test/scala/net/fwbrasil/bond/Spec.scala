package net.fwbrasil.bond

import shapeless.Witness
import org.scalatest.FreeSpec
import org.scalatest.MustMatchers
import org.scalatest.Inside

class Spec extends FreeSpec with MustMatchers {

  val fails = shapeless.test.illTyped

  abstract class ValidatorTest[T, V](validator: Validator[T, V]) {

    def valids: List[T]
    def invalids: List[T]

    def validResult = validator(valids.head).get

    def typeLevelTests: Unit

    validator.toString - {

      "valid" - {
        for (valid <- valids) yield {
          valid.toString in {
            validator(valid).get: T with V
            validator(valid) mustEqual Valid(valid)
          }
        }
      }

      "invalid" - {
        for (invalid <- invalids) yield {
          invalid.toString in {
            validator(invalid) match {
              case Invalid(List(Violation(invalid, validator))) =>
            }
          }
        }
      }

      "type-level" - {
        typeLevelTests
      }
    }
  }
}