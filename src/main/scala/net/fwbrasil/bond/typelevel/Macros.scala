package net.fwbrasil.bond.typelevel

import scala.reflect.macros.blackbox.Context
import language.experimental.macros
import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import java.lang.reflect.Field
import shapeless.SingletonTypeUtils


object Macros {
  def cast[T](value: Any): T = macro Macros.castImpl[T]
}

class Macros(val c: blackbox.Context) extends SingletonTypeUtils {
  import c.universe._

  def castImpl[V: WeakTypeTag](value: Expr[Any]): Tree = {
//    val a = value.actualType.baseType(value.actualType.baseClasses(1))
//    val w = weakTypeOf[T]
//    val b = a.typeArgs.filter(_ => true)
//    val ConstantType(Constant(x: String)) = a.typeArgs.head 
    c.warning(c.enclosingPosition, showRaw(value.actualType))
    
//    val q"${tpeString: String}" = tpeSelector
//    val tpe =
//      parseLiteralType(tpeString)
//        .getOrElse(c.abort(c.enclosingPosition, s"Malformed literal $tpeString"))
//
//    typeCarrier(tpe)

    q"???"
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
