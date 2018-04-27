package w1

import java.util.concurrent.ConcurrentHashMap

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future}

trait Repository[T, ID] {
  def put(item: T): Future[Unit]

  def get(id: ID): Future[T]

  def all(): Future[Seq[T]]
}

trait DataRepository extends Repository[Data, String]

class MemoryDataRepository(implicit ec: ExecutionContext) extends DataRepository {
  private[this] val storage = new ConcurrentHashMap[String, Data]()

  override def put(item: Data): Future[Unit] = Future {
    storage.put(item.key, item)
  }

  override def get(key: String): Future[Data] = Future {
    Option(storage.get(key)).getOrElse(throw new NoSuchElementException(s"$key not found"))
  }

  override def all(): Future[Seq[Data]] = Future {
    storage.values().asScala.toSeq
  }
}

class PersistentDataRepository(storage: Storage[Data])(implicit ec: ExecutionContext) extends DataRepository {
  override def put(item: Data): Future[Unit] = storage.write(item)

  override def get(key: String): Future[Data] = storage.readAll.map(_.find(_.key == key) match {
    case Some(item) => item
    case _ => throw new NoSuchElementException(s"$key not found")
  })

  override def all(): Future[Seq[Data]] = storage.readAll
}