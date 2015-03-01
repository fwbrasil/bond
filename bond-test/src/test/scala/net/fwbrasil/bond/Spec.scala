package net.fwbrasil.bond

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers
import org.scalatest.Inside

class Spec extends FreeSpec with MustMatchers with Inside {
  
  abstract class ValidatorTest[T, V](validator: Validator[T, V]) {

    def valids: List[T]
    def invalids: List[T]

    def valid = valids.head
    def validValue = validator.validate(valids.head).get

    def typeLevelTests = {}

    validator.toString - {

      "valid" - {
        for (valid <- valids) yield {
          s"$valid" in {
            validator.validate(valid).get: T with V
            validator.validate(valid) mustEqual Valid(valid)
          }
        }
      }

      "invalid" - {
        for (invalid <- invalids) yield {
          s"$invalid" in {
            validator.validate(invalid) match {
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