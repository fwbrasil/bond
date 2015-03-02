package net.fwbrasil.bond

class BooleansSpec extends Spec {

  new ValidatorTest(False) {

    def valids = List(false)
    def invalids = List(true)

    override def typeLevelTests = {
      "doesn't cast from true" in {
        "True(true).get: Boolean with False" mustNot typeCheck
      }
    }
  }

  new ValidatorTest(True) {

    def valids = List(true)
    def invalids = List(false)

    override def typeLevelTests =
      "doesn't cast from false" in {
        "False(false).get: Boolean with True" mustNot typeCheck
      }
  }
}
 