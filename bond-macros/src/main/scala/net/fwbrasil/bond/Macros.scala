package net.fwbrasil.bond

import scala.reflect.macros.whitebox.Context
import scala.util.Failure
import scala.util.Success
import scala.util.Try

import net.fwbrasil.smirror.SInstanceMethod
import net.fwbrasil.smirror.runtimeMirror
import net.fwbrasil.smirror.sClassOf

object Macros {

  private val classLoader = getClass.getClassLoader
  private implicit lazy val mirror = runtimeMirror(classLoader)

  def lift[U, M](c: Context)(value: c.Expr[U])(implicit u: c.WeakTypeTag[U], m: c.WeakTypeTag[M]): c.Tree = {
    import c.universe._

    isValid[U, M](c) match {
      case Success(true) =>
      // ok
      case Success(false) =>
        c.error(c.enclosingPosition, s"The lifting of '${u.tpe}'' to '${m.tpe}' is not valid (Bond)")
      case Failure(ex) =>
        c.error(c.enclosingPosition, s"'${u.tpe}' is not liftable to '${m.tpe}' (Bond)")
        c.error(c.enclosingPosition, ex.getMessage)
    }

    q"""
      $value.asInstanceOf[$u with $m]
    """
  }

  private def isValid[U, M](c: Context)(implicit u: c.WeakTypeTag[U], m: c.WeakTypeTag[M]) = {
    import c.universe._
    for {
      origin <- extractConstantTypeArg(c)(weakTypeTag[U].tpe.baseType(weakTypeTag[M].tpe.baseClasses.head))
      target <- extractConstantTypeArg(c)(weakTypeTag[M].tpe)
      lift <- findLiftMethod(c)(weakTypeTag[M].tpe)
      valid <- validateLift(lift, origin, target)
    } yield {
      valid
    }
  }

  private def extractConstantTypeArg(c: Context)(tpe: c.Type) =
    Try {
      import c.universe._
      tpe.typeArgs match {
        case List(ConstantType(Constant(constant: Object))) => constant
        case _ => throw new IllegalStateException(s"Expected a single constant type arg, but got ${tpe.typeArgs} for $tpe")
      }
    }

  private def findLiftMethod(c: Context)(tpe: c.Type) =
    Try {
      val cls = classLoader.loadClass(tpe.baseClasses.head.fullName)
      sClassOf(cls).companionObjectOption.flatMap(_.methods.find(_.name == "lift")).get
    }

  private def validateLift(method: SInstanceMethod[_], origin: Object, target: Object) =
    Try {
      method.invoke(origin, target).asInstanceOf[Boolean]
    }
}