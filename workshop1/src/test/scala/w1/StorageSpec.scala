package w1

import java.io.File

import org.scalamock.scalatest.AsyncMockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{AsyncFlatSpec, BeforeAndAfterEach, Matchers}

import scala.io.Source

class StorageSpec extends AsyncFlatSpec with Matchers with AsyncMockFactory with BeforeAndAfterEach with ScalaFutures {
  val fileName = "TempFile.txt"

  override def afterEach(): Unit = {
    val file = new File(fileName)
    file.delete()
  }

  def withContext[T](f: (Converter[Data], Storage[Data]) => T): T = {
    implicit val converter: Converter[Data] = mock[Converter[Data]]
    val storage = new FileStorage[Data](fileName)

    f(converter, storage)
  }

  "FileStorage" should "write in file" in withContext { (converter, storage) =>
    converter.encode _ expects Data("foo", "bar") returning "data".getBytes
    storage.write(Data("foo", "bar")).map({ _ =>
      val file = new File(fileName)
      Source.fromFile(file, "utf-8").mkString shouldBe "data\n"
    })
  }

  "FileStorage" should "read from file" in withContext { (converter, storage) =>
    converter.encode _ expects Data("foo", "bar") returning "data".getBytes
    converter.decode _ expects where { (data: Array[Byte]) =>
      new String(data) == "data"
    } returning Data("bar", "foo")

    for {
      _ <- storage.write(Data("foo", "bar"))
      seq <- storage.readAll
    } yield seq shouldBe Seq(Data("bar", "foo"))
  }

}
