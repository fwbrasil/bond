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
  //  lazy val tb = mirror.mkToolBox(frontEnd, options)

  def lift[T, U, M](c: Context)(value: c.Expr[U])(
    implicit t: c.WeakTypeTag[T], u: c.WeakTypeTag[U], m: c.WeakTypeTag[M]) = {

    import c.universe._

    c.inferImplicitView(value.tree, value.tree.tpe, t.tpe)

    isValid[U, M](c) match {
      case Success(true) =>
      // ok
      case Success(false) =>
        c.error(c.enclosingPosition,
          s"The lifting of '${u.tpe}'' to '${m.tpe}' is not valid (Bond)")
      case Failure(ex) =>
        c.error(c.enclosingPosition,
          s"'${u.tpe}' is not liftable to '${m.tpe}' - ${ex.getMessage} (Bond)")
    }

    q"""
      $value.asInstanceOf[${value.actualType} with $m]
    """
  }

  private def isValid[U, M](c: Context)(implicit u: c.WeakTypeTag[U], m: c.WeakTypeTag[M]) =
    for {
      origin <- extractConstantTypeArg(c)(u.tpe.baseType(m.tpe.baseClasses.head))
      target <- extractConstantTypeArg(c)(m.tpe)
      valid <- tryFastJavaReflection[U, M](c)(origin, target)
    } yield {
      valid
    }

  private def tryFastJavaReflection[U, M](c: Context)(
    origin: c.universe.Constant, target: c.universe.Constant)(
      implicit u: c.WeakTypeTag[U], m: c.WeakTypeTag[M]) = {
    import c.universe._
    for {
      lift <- findLiftMethod(c)(weakTypeTag[M].tpe)
      valid <- validateLift(lift, origin.value, target.value)
    } yield {
      valid
    }
  }

  private def extractConstantTypeArg(c: Context)(tpe: c.Type) =
    Try {
      import c.universe._
      tpe.typeArgs match {
        case List(ConstantType(c @ Constant(value))) =>
          c
        case _ =>
          throw new IllegalStateException(
            s"Expected a single constant type arg, but got ${tpe.typeArgs} for $tpe")
      }
    }

  private def findLiftMethod(c: Context)(tpe: c.Type) =
    Try {
      val cls = classLoader.loadClass(tpe.baseClasses.head.fullName)
      val Some(method) = sClassOf(cls).companionObjectOption.flatMap(_.methods.find(_.name == "lift"))
      method
    }

  private def validateLift(method: SInstanceMethod[_], origin: Any, target: Any) =
    Try {
      method.invoke(origin, target).asInstanceOf[Boolean]
    }
}