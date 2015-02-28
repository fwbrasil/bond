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