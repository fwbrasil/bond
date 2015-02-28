package net.fwbrasil.bond

import shapeless.Witness
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import org.specs2.specification.Example
import org.specs2.execute.AsResult

class Spec extends Specification {

  val fails = shapeless.test.illTyped
  
  abstract class ValidatorTest[T, V](validator: Validator[T, V]) {

    val valid: T
    val invalid: T
    
    def validResult = validator(valid).get
    
    def typeLevelTests: Example

    validator.toString >> {

        "success" in {
          validator(valid) mustEqual Valid(valid)
          validator(valid).get: T with V
          ok
        }

        "failure" in {
          validator(invalid) must beLike {
            case Invalid(List(Violation(invalid, validator))) => ok
          }
        }
        
        "type-level" >> {
          typeLevelTests
        }
    }
  }
}