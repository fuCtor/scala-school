package w1

import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class RepositorySpec extends FlatSpec with ScalaFutures with Matchers with MockFactory {

  trait ContextA {
    val repo = new MemoryDataRepository()
  }

  trait ContextB {
    val storage = mock[Storage[Data]]
    val repo = new PersistentDataRepository(storage)
  }

  implicit val defaultPatience = PatienceConfig(timeout = Span(2, Seconds), interval = Span(5, Millis))

  "MemoryRepository" should "store item" in new ContextA {
    repo.put(Data("foo", "bar")).map(_ => succeed)
  }

  "MemoryRepository" should "return stored item" in new ContextA {
    val f = for {
      _ <- repo.put(Data("foo", "bar"))
      item <- repo.get("foo")
    } yield item.key

    f.futureValue shouldBe "foo"
  }

  "MemoryRepository" should "fail for unknown key" in new ContextA {
    repo.get("foo").failed.futureValue shouldBe a[NoSuchElementException]
  }

  "PersistentDataRepository" should "store item via storage" in new ContextB {
    storage.write _ expects Data("foo", "bar") returning Future.unit
    repo.put(Data("foo", "bar")).map(_ => succeed).futureValue
  }

  "PersistentDataRepository" should "return item stored item via storage" in new ContextB {
    storage.write _ expects Data("foo", "bar") returning Future.unit
    storage.readAll _ expects() returning Future.successful(Seq(Data("foo", "bar")))

    val f = for {
      _ <- repo.put(Data("foo", "bar"))
      item <- repo.get("foo")
    } yield item.key

    f.futureValue shouldBe "foo"
  }

  "PersistentDataRepository" should "fail for unknown key" in new ContextB {
    storage.readAll _ expects() returning Future.successful(Seq.empty)

    repo.get("foo").failed.futureValue shouldBe a[NoSuchElementException]
  }

}
