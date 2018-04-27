package w1

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {

  import Request._
  import scala.concurrent.ExecutionContext.Implicits.global

  val repo = new MemoryDataRepository
  implicit val printer = new ConsolePrinter
  val controller = new DataController(repo)

  val f = for {
    _ <- controller(Add(Data("a", "b")))
    _ <- controller(Add(Data("d", "b")))
    _ <- controller(Add(Data("e", "b")))
    _ <- controller(Add(Data("b", "c")))
    _ <- controller(PrintAll)
    _ <- controller(GroupByValue)
    _ <- controller(Get("a"))
    _ <- controller(Get("c"))
  } yield {}

  Await.ready(f, Duration.Inf)
}
