package net.fwbrasil

import net.fwbrasil.bond.validation._

package object bond
  extends Validator
  with Booleans
  with Iterables
  with Objects
  with Strings
  with Numbers
  with App {

  val a =
    for {
      a <- Email("a@a.com")
      b <- NonEmpty(a)
    } yield {
      b
    }

  println(a.get)

}
