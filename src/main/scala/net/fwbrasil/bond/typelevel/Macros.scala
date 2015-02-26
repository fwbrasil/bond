package net.fwbrasil.bond.typelevel

import scala.reflect.macros.blackbox.Context
import language.experimental.macros
import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import java.lang.reflect.Field
import shapeless.SingletonTypeUtils

class Macros(val c: blackbox.Context) extends SingletonTypeUtils {
  import c.universe._

  def lift[V[_], A, I, B, O](value: c.Expr[I with V[A]])(
    implicit v: WeakTypeTag[V[_]],
    a: WeakTypeTag[A],
    i: WeakTypeTag[I],
    b: WeakTypeTag[B],
    o: WeakTypeTag[O]): c.Expr[O] = {
    //    val a = value.actualType.baseType(value.actualType.baseClasses(1))
    //    val w = weakTypeOf[T]
    //    val b = a.typeArgs.filter(_ => true)
    //    val ConstantType(Constant(x: String)) = a.typeArgs.head 
//    c.warning(c.enclosingPosition, showRaw(a))
//    c.warning(c.enclosingPosition, showRaw(u))
//    c.warning(c.enclosingPosition, showRaw(b))
    println(showRaw(a)) 
    println(showRaw(value.actualType.baseType(value.actualType.baseClasses(1))))

    //    val q"${tpeString: String}" = tpeSelector
    //    val tpe =
    //      parseLiteralType(tpeString)
    //        .getOrElse(c.abort(c.enclosingPosition, s"Malformed literal $tpeString"))
    //
    //    typeCarrier(tpe)

    reify {
      value.splice.asInstanceOf[O]
    }
  }

  //  private def inspect(v: Any) = {
  //    val cls = v.getClass
  //    val f = fields(cls)
  //    f.foreach(print)
  ////    f.map(_.get(v)).foreach(print)
  //  }
  //  
  //  private def fields(c: Class[_]): Array[Field] = {
  //    c.getDeclaredFields ++ fields(c.getSuperclass)
  //  }
  //  
  //  private def print(b: Any) = {
  ////    val s = try b.toString
  ////    catch {
  ////      case e => "error"
  ////    }
  //    c.warning(c.enclosingPosition, "a")
  //  }
}
