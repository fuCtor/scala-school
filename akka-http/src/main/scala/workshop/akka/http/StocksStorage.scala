package workshop.akka.http

import scala.concurrent.Future

trait StocksStorage {
  def getAll(): Future[Seq[String]]

  def add(id: String): Future[Seq[String]]

  def remove(id: String): Future[Seq[String]]
}

class StockStorageImpl() extends StocksStorage {
  @volatile private var storage = Vector[String]()

  override def getAll(): Future[Seq[String]] = synchronized {
    Future.successful(storage)
  }

  override def add(id: String): Future[Seq[String]] = synchronized {
    if(storage.contains(id)) Future.successful(storage)
    else {
      storage = storage :+ id
      Future.successful(storage)
    }
  }

  override def remove(id: String): Future[Seq[String]] = synchronized {
    storage = storage.filterNot(_ == id)
    Future.successful(storage)
  }
}
