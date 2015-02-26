package net.fwbrasil.bond.typelevel

import scala.reflect.macros.whitebox.Context
import language.experimental.macros
import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import java.lang.reflect.Field
import shapeless.SingletonTypeUtils

object Macros {
  def lift[V[_], O](c: Context)(value: c.Expr[Any])(
    implicit v: c.WeakTypeTag[V[_]],
    o: c.WeakTypeTag[O]): c.Expr[O] = {
    import c.universe._
    //    val a = value.actualType.baseType(value.actualType.baseClasses(1))
    //    val w = weakTypeOf[T]
    //    val b = a.typeArgs.filter(_ => true)
    //    val ConstantType(Constant(x: String)) = a.typeArgs.head 
    //    c.warning(c.enclosingPosition, showRaw(a))
    //    c.warning(c.enclosingPosition, showRaw(u))
    //    c.warning(c.enclosingPosition, showRaw(b))
    println(showRaw(value.actualType))
    println(showRaw(weakTypeOf[O]))

    //    val q"${tpeString: String}" = tpeSelector
    //    val tpe =
    //      parseLiteralType(tpeString)
    //        .getOrElse(c.abort(c.enclosingPosition, s"Malformed literal $tpeString"))
    //
    //    typeCarrier(tpe)

    reify {
      null.asInstanceOf[O]
    }
  }
}

//class Macros(val c: blackbox.Context) extends SingletonTypeUtils {
//  import c.universe._
//
//  def lift[O: WeakTypeTag](value: c.Expr[Any]): c.Expr[O]= {
//    //    val a = value.actualType.baseType(value.actualType.baseClasses(1))
//    //    val w = weakTypeOf[T]
//    //    val b = a.typeArgs.filter(_ => true)
//    //    val ConstantType(Constant(x: String)) = a.typeArgs.head 
//    //    c.warning(c.enclosingPosition, showRaw(a))
//    //    c.warning(c.enclosingPosition, showRaw(u))
//    //    c.warning(c.enclosingPosition, showRaw(b))
//    println(showRaw(value.actualType))
//    println(showRaw(weakTypeOf[O]))
//
//    //    val q"${tpeString: String}" = tpeSelector
//    //    val tpe =
//    //      parseLiteralType(tpeString)
//    //        .getOrElse(c.abort(c.enclosingPosition, s"Malformed literal $tpeString"))
//    //
//    //    typeCarrier(tpe)
//
//    reify {
//      null.asInstanceOf[O]
//    }
//  }
//
//  //  private def inspect(v: Any) = {
//  //    val cls = v.getClass
//  //    val f = fields(cls)
//  //    f.foreach(print)
//  ////    f.map(_.get(v)).foreach(print)
//  //  }
//  //  
//  //  private def fields(c: Class[_]): Array[Field] = {
//  //    c.getDeclaredFields ++ fields(c.getSuperclass)
//  //  }
//  //  
//  //  private def print(b: Any) = {
//  ////    val s = try b.toString
//  ////    catch {
//  ////      case e => "error"
//  ////    }
//  //    c.warning(c.enclosingPosition, "a")
//  //  }
//}
