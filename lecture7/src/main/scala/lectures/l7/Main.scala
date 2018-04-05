package lectures.l7

object Main extends App {
  {
    import v1._
//    val storage1: MemoryStorage[String] = MemoryStorage[String](Dog("a", "b")).persist(Dog("b", "c")).persist(Husky("x")).persist(Cat("b", "c"))
//    println(storage1)
  }

  {
    import v2._
//    val storage1: MemoryStorage[String, Dog] = MemoryStorage[String, Dog](Dog("a", "b")).persist(Dog("b", "c")).persist(Husky("x"))
//    println(storage1)
  }

  {
    import v3._
//    val storage1: MemoryStorage[String, Dog] = MemoryStorage[String, Dog](Dog("a", "b")).persist(Dog("b", "c")).persist(Husky("x"))
//    val storage2: MemoryStorage[String, Animal] = MemoryStorage[String, Dog](Dog("a", "b")).persist(Cat("b", "c"))
//    println(storage1)
//    println(storage2)
  }

  {
    import v4._
//    val storage1 = MemoryStorage[String, Dog](Dog("a", "b")).persist(Dog("b", "c"))
//    val storage2 = MemoryStorage[String, Husky](Husky("x"))
//    val storage3 = MemoryStorage[String, Dog](Dog("d", "e")).persist(Cat("f", "g"))
//    val storage4 = storage1.concat(storage2).concat(storage3)
//    println(storage4)
  }

  {
    import v5._
//    val storage1: MemoryStorage[String, Dog] = MemoryStorage[String, Dog](Dog("a", "b")).persist(Dog("b", "c")).persist(Husky("x"))
//    val storage2: MemoryStorage[String, Animal] = MemoryStorage[String, Dog](Dog("d", "e")).persist(Cat("f", "g"))
//    storage1.concat(storage2).foreach({
//      case (name, item) => println(name, item)
//    })
  }
}
