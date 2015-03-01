package net.fwbrasil.bond

class ResultSpec extends Spec {

  "permits to apply multiple validations" in {
    val result =
      for {
        even <- Even.validate(2)
        prime <- Prime.validate(even)
      } yield {
        prime
      }
    inside(result) {
      case Valid(value: Int with Even with Prime) =>
        value mustEqual 2
    }
  }

  "accumulates validation errors" in {
    val result =
      for {
        v1 <- Even.validate(1)
        v2 <- Odd.validate(2)
      } yield {
        1
      }
    inside(result) {
      case Invalid(evenViolation :: oddViolation :: Nil) =>
        evenViolation.value mustEqual 1
        oddViolation.value mustEqual 2
        evenViolation.validator mustEqual Even
        oddViolation.validator mustEqual Odd
    }
  }

  "accumulates errors for partially failed validations" in {

    val result =
      for {
        v1 <- Prime.validate(7723)
        v2 <- Perfect.validate(v1)
        v3 <- Even.validate(v2)
        v4 <- GreaterThan(10).validate(v3)
      } yield {
        v4
      }
    inside(result) {
      case Invalid(perfectViolation :: evenViolation :: Nil) =>
        perfectViolation.value mustEqual 7723
        perfectViolation.validator mustEqual Perfect
        evenViolation.value mustEqual 7723
        evenViolation.validator mustEqual Even
    }
  }

  "permits to unsafely get the result" - {

    "success" in {
      val value: Int with Even = Even.validate(2).get
      value mustEqual 2
    }

    "failure" in {
      val error = intercept[ViolationsException](Even.validate(1).get)
      inside(error) {
        case ViolationsException(List(violation)) =>
          violation.value mustEqual 1
          violation.validator mustEqual Even
      }
    }
  }

}