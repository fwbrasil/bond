package net.fwbrasil.bond

import shapeless.Witness
import org.scalatest.FreeSpec
import org.scalatest.MustMatchers
import org.scalatest.Inside

class Spec extends FreeSpec with MustMatchers {

  abstract class ValidatorTest[T, V](validator: Validator[T, V]) {

    def valids: List[T]
    def invalids: List[T]

    def valid = valids.head
    def validValue = validator(valids.head).get

    def typeLevelTests = {}

    validator.toString - {

      "valid" - {
        for (valid <- valids) yield {
          s"$valid" in {
            validator(valid).get: T with V
            validator(valid) mustEqual Valid(valid)
          }
        }
      }

      "invalid" - {
        for (invalid <- invalids) yield {
          s"$invalid" in {
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