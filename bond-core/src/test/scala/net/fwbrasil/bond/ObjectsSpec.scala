package net.fwbrasil.bond

class ObjectsSpec extends Spec {

  new ValidatorTest(IsNull) {
    def valids = List(null)
    def invalids = List(1, "a", new Object)
  }

  new ValidatorTest(IsNotNull) {
    def valids = List(1, "a", new Object)
    def invalids = List(null)
  }
}
