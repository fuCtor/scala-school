package lectures.l7

trait Item[T] {
  def id: T
}

class Animal(val name: String) extends Item[String] {
  override def id: String = name

  override def toString: String = s"Animal($id)"
}

class Dog(name: String, kind: String) extends Animal(name) {
  override def toString: String = s"Dog($name, $kind)"
}
class Husky(name: String) extends Dog(name, "husky") {
  override def toString: String = s"Husky($name)"
}

class Cat(name: String, kind: String) extends Animal(name) {
  override def toString: String = s"Cat($name, $kind)"
}

object Dog {
  def apply(name: String, kind: String): Dog = new Dog(name, kind)
}

object Husky {
  def apply(name: String): Husky = new Husky(name)
}

object Cat {
  def apply(name: String, kind: String): Cat = new Cat(name, kind)
}
