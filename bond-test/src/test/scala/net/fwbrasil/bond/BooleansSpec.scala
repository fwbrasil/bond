package net.fwbrasil.bond

class BooleansSpec extends Spec {

  new ValidatorTest(False) {

    val valid = false
    val invalid = true

    def typeLevelTests =
      "doesn't cast to true" in {
        fails("True(true).get: Boolean with False")
        ok
      }
  }

  new ValidatorTest(True) {

    val valid = true
    val invalid = false

    def typeLevelTests =
      "doesn't cast to false" in {
        fails("False(false).get: Boolean with True")
        ok
      }
  }
}
