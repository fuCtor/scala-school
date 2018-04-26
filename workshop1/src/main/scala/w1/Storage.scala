package w1

import java.io.{FileInputStream, FileOutputStream}

import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source

trait Storage[T] {
  def readAll: Future[Seq[T]]

  def write(data: T): Future[Unit]
}

final class FileStorage[T](fileName: String)(implicit ec: ExecutionContext, converter: Converter[T]) extends Storage[T] {
  override def readAll: Future[Seq[T]] = Future {
    Option(new FileInputStream(fileName)) match {
      case Some(stream) =>
        Source.fromInputStream(stream).getLines().map(line => converter.decode(line.getBytes)).toList
      case _ => Seq.empty
    }
  }

  override def write(data: T): Future[Unit] = Future {
    val stream = new FileOutputStream(fileName, true)
    stream.write(converter.encode(data))
    stream.write('\n')
  }
}
