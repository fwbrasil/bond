package net.fwbrasil.bond.typelevel

import scala.reflect.macros.whitebox.Context
import language.experimental.macros
import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import java.lang.reflect.Field
import shapeless.SingletonTypeUtils

object Macros {

  def lift[U, M](c: Context)(value: c.Expr[U])(
    implicit u: c.WeakTypeTag[U],
    m: c.WeakTypeTag[M]): c.Expr[U with M] = {
    import c.universe._
    
    val List(ConstantType(Constant(origin: Object))) = weakTypeTag[U].tpe.baseType(weakTypeTag[M].tpe.baseClasses.head).typeArgs
    val List(ConstantType(Constant(target: Object))) = weakTypeTag[M].tpe.typeArgs
    
    val name = weakTypeTag[M].tpe.baseClasses.head.companion.fullName
    val cls = Class.forName(name + "$")
    val module = cls.getField("MODULE$").get(null)
    val method = cls.getMethod("isValid", origin.getClass, target.getClass)
    val valid = method.invoke(module, origin, target).asInstanceOf[Boolean]
    if(!valid)
      c.error(c.enclosingPosition, s"fail to lift $origin to $target")
    
    reify {
      value.splice.asInstanceOf[U with M]
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
