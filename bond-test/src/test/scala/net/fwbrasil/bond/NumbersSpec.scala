package net.fwbrasil.bond

class NumbersSpec extends Spec {

  new ValidatorTest(GreaterThan(5)) {

    def valids = List(10d, 20d)
    def invalids = List(5d, 1d)

    def typeLevelTests = {
      "lifts to GreaterThan(1)" in {
        GreaterThan(1).lift(validResult)
      }
      "doesn't lift to GreaterThan(20)" in {
        "GreaterThan(5).lift(validResult)" mustNot typeCheck
      }
    }
  }
}
