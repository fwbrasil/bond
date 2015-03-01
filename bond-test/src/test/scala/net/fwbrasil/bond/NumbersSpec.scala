package net.fwbrasil.bond

class NumbersSpec extends Spec {

  new ValidatorTest(GreaterThan(5)) {

    def valids = List(10, 20, 30l)
    def invalids = List(5d, 1, 3l)

    override def typeLevelTests = {
      "lifts to GreaterThan(1)" in {
        val value: Int with GreaterThan[T.`1`.T] with GreaterThan[T.`5`.T] =
          GreaterThan(1).lift(GreaterThan(5).validate(6).get)
      }
      "doesn't lift to GreaterThan(20)" in {
        "GreaterThan(5).lift(validResult)" mustNot typeCheck
      }
    }
  }

  new ValidatorTest(GreaterThanOrEqual(5)) {

    def valids = List(5d, 20, 30l)
    def invalids = List(4d, 1, 3l)

    override def typeLevelTests = {
      "lifts to GreaterThan(1)" in {
        GreaterThanOrEqual(1).lift(validValue)
      }
      "lifts to GreaterThan(5)" in {
        GreaterThanOrEqual(5).lift(validValue)
      }
      "doesn't lift to GreaterThan(20)" in {
        "GreaterThan(5).lift(validResult)" mustNot typeCheck
      }
    }
  }

  new ValidatorTest(LesserThan(5)) {

    def valids = List(1d, 2, 3l)
    def invalids = List(5d, 6, 7l)

    override def typeLevelTests = {
      "lifts to LesserThan(6)" in {
        LesserThan(6).lift(validValue)
      }
      "doesn't lift to LesserThan(20)" in {
        "LesserThan(1).lift(validResult)" mustNot typeCheck
      }
    }
  }

  new ValidatorTest(LesserThanOrEqual(5)) {

    def valids = List(1d, 5, 3l)
    def invalids = List(8d, 6, 7l)

    override def typeLevelTests = {
      "lifts to LesserThanOrEqual(6)" in {
        LesserThanOrEqual(6).lift(validValue)
      }
      "lifts to LesserThanOrEqual(5)" in {
        LesserThanOrEqual(5).lift(validValue)
      }
      "doesn't lift to LesserThan(20)" in {
        "LesserThanOrEqual(1).lift(validResult)" mustNot typeCheck
      }
    }
  }

  new ValidatorTest(Odd) {
    def valids = List(1, 9, 11, 235)
    def invalids = List(2, 3332, 22)
  }

  new ValidatorTest(Even) {
    def valids = List(2, 3332, 22)
    def invalids = List(1, 9, 11, 235)
  }

  new ValidatorTest(Prime) {
    def valids = List(2, 3, 7723)
    def invalids = List(1, 7722)
  }

  new ValidatorTest(Perfect) {
    def valids = List(6, 496, 33550336)
    def invalids = List(1, 7722)
  }
}
