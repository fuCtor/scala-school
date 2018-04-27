package w1

import scala.concurrent.{ExecutionContext, Future}

trait Controller[Req, Resp] {
  def apply(request: Req)(implicit ec: ExecutionContext): Future[Resp]
}

sealed trait Request

object Request {

  case class Add(item: Data) extends Request

  case class Get(key: String) extends Request

  case object PrintAll extends Request

  case object GroupByValue extends Request

}

trait Printer {
  def println(v: Any): Unit
}

class ConsolePrinter extends Printer {
  override def println(v: Any): Unit = Console.println(v)
}

final class DataController(storage: DataRepository)(implicit printer: Printer) extends Controller[Request, Unit] {
  override def apply(request: Request)(implicit ec: ExecutionContext): Future[Unit] = request match {
    case Request.PrintAll => storage.all().map(_.foreach(printer.println))
    case Request.GroupByValue => storage.all().map(_.groupBy(_.value).foreach(p => printer.println(p._2.toList)))
    case Request.Add(item) => storage.put(item)
    case Request.Get(key) => storage.get(key).map(printer.println).recover {
      case _: NoSuchElementException => printer.println("Not Found")
    }
  }
}