package net.fwbrasil.bond

class StringsSpec extends Spec {

  new ValidatorTest(StartsWith("aa")) {

    def valids = List("aa")
    def invalids = List("bb")

    def typeLevelTests = {
      "lifts to StartsWith('aa')" in {
        StartsWith("a").lift(validResult): String with StartsWith[T.`"a"`.T]
      }
      "lifts to StartsWith('a')" in {
        StartsWith("a").lift(validResult): String with StartsWith[T.`"a"`.T]
      }
      "doesn't lift to StartsWith('b')" in {
        fails("""StartsWith("b").lift(validResult)""")
      }
    }
  }
}
