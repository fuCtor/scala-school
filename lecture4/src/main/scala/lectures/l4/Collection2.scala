package lectures.l4

object Collection2 extends App {

  sealed trait Transport

  case object Auto extends Transport

  case class Track(weight: Int) extends Transport

  // Реализовать функцию, выбирающую из списка только легковые машины и грузовики с весом меньше максимального
  // Релизовать тремя способами
  // - collect
  // - fold*
  // - map/flatMap
  // - filter

  def collectorA(seq: Seq[Transport], maxWeight: Int): Seq[Transport] = ???

  def collectorB(seq: Seq[Transport], maxWeight: Int): Seq[Transport] = ???

  def collectorC(seq: Seq[Transport], maxWeight: Int): Seq[Transport] = ???


  val transports  = Seq(
    Auto, Auto, Auto, Track(100), Auto, Track(150), Track(110), Track(200)
  )

  println(collectorA(transports, 120)) // => Auto, Auto, Auto, Track(100), Auto, Track(110)
  println(collectorB(transports, 120)) // => Auto, Auto, Auto, Track(100), Auto, Track(110)
  println(collectorC(transports, 120)) // => Auto, Auto, Auto, Track(100), Auto, Track(110)

}
