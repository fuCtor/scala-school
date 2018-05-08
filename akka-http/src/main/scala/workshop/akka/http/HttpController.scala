package workshop.akka.http

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.jackson.Serialization
import org.json4s.{DefaultFormats, jackson}

class HttpController(tradingService: TradingService) extends Json4sSupport {

  implicit val format: DefaultFormats.type = DefaultFormats
  implicit val serialization: Serialization.type = jackson.Serialization

  val routes: Route = pathPrefix("api") {
    pathPrefix("stocks") {
      get {
        complete(tradingService.stocks())
      }
    } ~ pathPrefix("stock") {
      get {
        pathPrefix("info") {
          complete(tradingService.stockPricesInfo())
        } ~ parameter('id) { id =>
          complete(tradingService.stockInfo(id))
        }
      } ~ put {
        parameter('id) { id =>
          complete(tradingService.addStock(id))
        }
      } ~ delete {
        parameter('id) { id =>
          complete(tradingService.removeStock(id))
        }
      }
    }
  }

}
