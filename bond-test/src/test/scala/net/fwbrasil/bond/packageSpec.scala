package net.fwbrasil.bond

class packageSpec extends Spec {
  
  "provides an alias to shapeless Witness" in {
    T mustEqual shapeless.Witness
  }

  "creates singleton types" in {
    val v: AnyRef { type T = T.`1`.T } = T.`1`
  }
}
