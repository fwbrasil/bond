package net.fwbrasil.bond

class StringsSpec extends Spec {

  new ValidatorTest(Email) {
    def valids = List("a@a.com", "test@gmail.com")
    def invalids = List("te st@gmail.com", "banana")
  }

  new ValidatorTest(URL) {
    def valids = List("http://xxx.com", "ftp://yyy.edu")
    def invalids = List("orange", "htt://orange")
  }

  new ValidatorTest(StartsWith("aa")) {

    def valids = List("aa", "aaa")
    def invalids = List("bb", "baa")

    override def typeLevelTests = {
      "lifts to StartsWith('aa')" in {
        StartsWith("aa").lift(validValue): String with StartsWith[T.`"aa"`.T]
      }
      "lifts to StartsWith('a')" in {
        StartsWith("a").lift(validValue): String with StartsWith[T.`"a"`.T]
      }
      "doesn't lift to StartsWith('b')" in {
        """StartsWith("b").lift(validResult)""" mustNot typeCheck
      }
      "doesn't lift to null" in {
        "StartsWith(null).lift(validValue)" mustNot typeCheck
      }
    }
  }
  
  new ValidatorTest(EndsWith("aa")) {

    def valids = List("aa", "aaa")
    def invalids = List("bb", "aab")

    override def typeLevelTests = {
      "lifts to EndsWith('aa')" in {
        EndsWith("aa").lift(validValue): String with EndsWith[T.`"aa"`.T]
      }
      "lifts to EndsWith('a')" in {
        EndsWith("a").lift(validValue): String with EndsWith[T.`"a"`.T]
      }
      "doesn't lift to StartsWith('b')" in {
        """EndsWith("b").lift(validResult)""" mustNot typeCheck
      }
    }
  }
  
  new ValidatorTest(MatchesRegex("^[0-5][a-d]$")) {
    def valids = List("1b", "4d")
    def invalids = List("6b", "4j")
  }
}
