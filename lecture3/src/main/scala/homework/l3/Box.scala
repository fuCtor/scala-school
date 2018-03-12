package homework.l3

sealed trait Box
case class PlayStationBox() extends Box
case class GuitarBox() extends Box
case class EaselBox() extends Box
case class BasicBox() extends Box {
  val size: Int = 4
}
case class BigBox() extends Box {
  val size: Int = 10
}

trait Stuff
case class PlayStation() extends Stuff
case class Guitar() extends Stuff
case class TV(size: Int) extends Stuff
case class Easel() extends Stuff
case class Book() extends Stuff
case class Cat() extends Stuff
case class Uculele() extends Stuff
case class Dish() extends Stuff
case class Shoes() extends Stuff

object BoxPlan {
  def plan(stuff: Seq[Stuff]): Seq[Box] = ???
}