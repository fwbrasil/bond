Bond  
====  
Type-level validation for Scala  
[![Build Status](https://secure.travis-ci.org/fwbrasil/bond.png)](http://travis-ci.org/fwbrasil/bond)
[![Codacy Badge](https://www.codacy.com/project/badge/eb4d686ffc7949b48516a806f0e2412c)](https://www.codacy.com/public/fwbrasil/bond)
[![Join the chat at https://gitter.im/fwbrasil/bond](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/fwbrasil/bond?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Bond provides a mechanism to express validation constraints by using the type system. A quick example:

```scala
import net.fwbrasil.bond._

case class Employee (
    name: String with NonEmpty,
    email: String with Email,
    age: Int with GreaterThan[T.`14`.T]
)

case class Company (
    employees: List[Employee] with MinSize[T.`2`.T]
)
```

> *Note*: Scala does not have support for easily defining singleton types. The ```T.`2`.T``` definition is a shortcut for ```shapeless.Witness.`2`.T```, that produces the singleton type by using a macro. It is possible to define singleton types for `String` as well: ```T.`"a"`.T```.

The type-level computations allow the application of constraint transformations at compile time. The 'lift' method is used to apply such transformations:

```scala
import net.fwbrasil.bond._

val employeeAge: Int with GreaterThan[T.`14`.T] = ...

val lifted: Int with GreaterThan[T.`12`.T] =
    GreaterThan(12).lift(employee.age)
```

Observe that `GreaterThan(14)` is liftable to `GreaterThan(12)` because any number that is greater than `14` will be greater than `12`. The transformation is not applicable for `GreaterThan(18)`:

```scala
GreaterThan(18).lift(employee.age)

[error] The lifting of 'Int with net.fwbrasil.bond.GreaterThan[Int(14)]'' to 'net.fwbrasil.bond.GreaterThan[Int(18)]' is not valid (Bond)
[error]         GreaterThan(18).lift(employee.age)
[error]                             ^
```

As a previous step of type-level validations, it is necessary to apply value-level validations. In an ideal application, the value-level validations should happen on the boundaries where it is necessary to interface with non-validated values. For instance, the boundaries could be the user interface or a database interaction.

Bond provides a validation DSL that is capable of accumulating multiple errors:

```scala
import net.fwbrasil.bond._

object controller {

    def createEmployeeRestEndpoint(name: String, email: String, age: Int) =
        applyValueLevelValidations(name, email, age) match {
            case Valid((name, email, age)) => render.json(Employee(name, email, age))
            case Invalid(violations)       => render.badRequest(violations)
        }

    private def applyValueLevelValidations(name: String, email: String, age: Int): Result[(String with NonEmpty, String with Email, Int with GreaterThan[T.`14`.T])] =
        for {
            name <- NonEmpty.validate(name)
            email <- Email.validate(email)
            age <- GreaterThan(14).validate(age)
        } yield {
            (name, email, age)
        }

    private object render { 
        def json(v: Any) = println(s"OK: $v")
        def badRequest(v: Any) = println(s"BadRequest: $v") 
    } 
}
```

Execution examples:

```scala
scala> controller.createEmployeeRestEndpoint("", "b", 1)
BadRequest: List(Violation(,NonEmpty), Violation(b,Email), Violation(1,GreaterThan(14)))

scala> controller.createEmployeeRestEndpoint("a", "b", 1)
BadRequest: List(Violation(b,Email), Violation(1,GreaterThan(14)))

scala> controller.createEmployeeRestEndpoint("a", "b@a.com", 1)
BadRequest: List(Violation(1,GreaterThan(14)))

scala> controller.createEmployeeRestEndpoint("a", "b@a.com", 17)
OK: Employee(a,b@a.com,17)
```

# Getting started #

To use bond, just add the dependency to the project's build configuration.

__Important__: Bond is available only for Scala `2.11.x`. Change ```x.x.x``` with the latest version listed in the [CHANGELOG.md](https://github.com/fwbrasil/bond/blob/master/CHANGELOG.md) file.

SBT

```scala
libraryDependencies ++= Seq(
  "net.fwbrasil" %% "bond" % "x.x.x"
)
```

Maven

```xml
<dependency>
    <groupId>net.fwbrasil</groupId>
    <artifactId>bond</artifactId>
    <version>x.x.x</version>
</dependency>
```

# Built-in Validations #

*[Iterables](https://github.com/fwbrasil/bond/blob/v0.0.1/src/main/scala/net/fwbrasil/bond/Iterables.scala)*

These validators can be used with any type that is convertible to `Iterable[_]`. This includes `List`, `Seq`, `String` and many other types.

* NonEmpty
* Empty
* Size(N)
* MinSize(N)
* MaxSize(N)

*[Numbers](https://github.com/fwbrasil/bond/blob/v0.0.1/src/main/scala/net/fwbrasil/bond/Numbers.scala)*

Validators to be used with any type that is convertible to `java.lang.Number`.

* GreaterThan(N)
* GreaterThanOrEqual(N)
* LesserThan(N)
* LesserThanOrEqual(N)
* Odd
* Even
* Prime
* Perfect

*[Strings](https://github.com/fwbrasil/bond/blob/v0.0.1/src/main/scala/net/fwbrasil/bond/Strings.scala)*

* CreditCard
* Email
* StartsWith(S)
* EndsWith(S)
* MatchesRegex(S)
* URI
* URL
* UUID

*[Booleans](https://github.com/fwbrasil/bond/blob/v0.0.1/src/main/scala/net/fwbrasil/bond/Booleans.scala)*

* False
* True

*[Objects](https://github.com/fwbrasil/bond/blob/v0.0.1/src/main/scala/net/fwbrasil/bond/Objects.scala)*

* IsNull
* IsNotNull

# Custom Validations #

Example of custom validation:

```scala
trait Adult
case object Adult
  extends Validator[Employee, Adult] {

  def isValid(e: Employee) =
    e.age >= 21
}
```

Example usage:

```scala
def registerForDangerousJob(employee: Employee with Adult) = ...
```

> **Important**: Scala does not provide a mechanism to define the macro expansion order and the lift macro depends on the validation class. This means that you need to manually guarantee that the validation class is compiled before the `lift` macro expansion. There are some workarounds to influence the compilation order:
> 
> * Use a separate source folder that compiles before the main source folder.
> * Define the custom validations inside a separate sub-module


# Versioning #

Bond adheres to Semantic Versioning 2.0.0. If there is a violation of this scheme, report it as a bug. Specifically, if a patch or minor version is released and breaks backward compatibility, that version should be immediately yanked and/or a new version should be immediately released that restores compatibility. Any change that breaks the public API will only be introduced at a major-version release.


# License #

See the [LICENSE-LGPL](https://github.com/fwbrasil/bond/blob/master/LICENSE-LGPL.txt) file for details.
