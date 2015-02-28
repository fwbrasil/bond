package net.fwbrasil.bond

class StringsSpec extends Spec {

  new ValidatorTest(StartsWith("aa")) {

    val valid = "aa"
    val invalid = "bb"

    def typeLevelTests = {
      "lifts to StartsWith('aa')" in {
        StartsWith("a").lift(validResult): String with StartsWith[T.`"a"`.T]
        ok
      }
      "lifts to StartsWith('a')" in {
        StartsWith("a").lift(validResult): String with StartsWith[T.`"a"`.T]
        ok
      }
      "doesn't lift to StartsWith('b')" in {
        fails("""StartsWith("b").lift(validResult)""")
        ok
      }
    }
  }
}
