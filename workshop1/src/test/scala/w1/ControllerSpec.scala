package w1

import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Future

class ControllerSpec extends FlatSpec with ScalaFutures with Matchers with MockFactory {

  import Request._

  import scala.concurrent.ExecutionContext.Implicits.global

  implicit val defaultPatience: PatienceConfig = PatienceConfig(timeout = Span(2, Seconds), interval = Span(5, Millis))

  trait Context {
    val repo: DataRepository = mock[DataRepository]
    implicit val printer: Printer = mock[ConsolePrinter]
    val controller = new DataController(repo)
  }

  "DataController" should "add new item" in new Context {
    repo.put _ expects Data("a", "b") returning Future.unit
    controller(Add(Data("a", "b"))).futureValue
  }

  "DataController" should "print item by key" in new Context {
    repo.get _ expects "a" returning Future.successful(Data("a", "b"))
    printer.println _ expects Data("a", "b")
    controller(Get("a")).futureValue
  }

  "DataController" should "print not found for wrong key" in new Context {
    repo.get _ expects "a" returning Future.failed(new NoSuchElementException())
    printer.println _ expects "Not Found"
    controller(Get("a")).futureValue
  }

  "DataController" should "print all items" in new Context {
    repo.all _ expects() returning Future.successful(Seq(Data("a", "b"), Data("b", "a")))
    inSequence {
      printer.println _ expects Data("a", "b")
      printer.println _ expects Data("b", "a")
    }

    controller(PrintAll).futureValue
  }

  "DataController" should "print grouped by value" in new Context {
    repo.all _ expects() returning Future.successful(Seq(Data("a", "b"), Data("c", "b"), Data("b", "a")))
    inSequence {
      printer.println _ expects List(Data("a", "b"), Data("c", "b"))
      printer.println _ expects List(Data("b", "a"))
    }

    controller(GroupByValue).futureValue
  }
}
