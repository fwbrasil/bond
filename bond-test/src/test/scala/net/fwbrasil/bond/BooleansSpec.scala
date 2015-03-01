package net.fwbrasil.bond

class BooleansSpec extends Spec {

  new ValidatorTest(False) {

    def valids = List(false)
    def invalids = List(true)

    def typeLevelTests =
      "doesn't cast to true" in {
        fails("True(true).get: Boolean with False")
      }
  }

  new ValidatorTest(True) {

    def valids = List(true)
    def invalids = List(false)

    def typeLevelTests =
      "doesn't cast to false" in {
        fails("False(false).get: Boolean with True")
      }
  }
}
