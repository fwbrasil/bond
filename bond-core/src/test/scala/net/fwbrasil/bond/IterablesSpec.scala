package net.fwbrasil.bond

class IterablesSpec extends Spec {

  new ValidatorTest(NonEmpty) {
    def valids = List(List(1), "a")
    def invalids = List(List(), "")
  }

  new ValidatorTest(Empty) {
    def valids = List(List(), "")
    def invalids = List(List(1), "a")
  }

  new ValidatorTest(Size(2)) {
    def valids = List(List(1, 2), "ab")
    def invalids = List(List(1), "a", "abc")

    override def typeLevelTests = {
      "doesn't lift to a different size" in {
        """Size(3).lift(validValue)""" mustNot typeCheck
      }
    }
  }

  new ValidatorTest(MinSize(2)) {
    def valids = List(List(1, 2), "abc")
    def invalids = List(List(1), "a")

    override def typeLevelTests = {
      "lifts to MinSize(3)" in {
        MinSize(1).lift(validValue)
      }
      "doesn't lift to MinSize(1)" in {
        """MinSize(3).lift(validValue)""" mustNot typeCheck
      }
    }
  }

  new ValidatorTest(MaxSize(2)) {
    def valids = List(List(1, 2), "a")
    def invalids = List(List(1, 2, 3), "abc")

    override def typeLevelTests = {
      "lifts to MinSize(3)" in {
        MaxSize(3).lift(validValue)
      }
      "doesn't lift to MinSize(1)" in {
        """MaxSize(1).lift(validValue)""" mustNot typeCheck
      }
    }
  }
}
