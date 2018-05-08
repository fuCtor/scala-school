package workshop.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import workshop.akka.http.trading.RestTradingApi
import akka.http.scaladsl.server.Directives._

object StockApplication {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    import system.dispatcher

    val httpClient = new AkkaHttpClient()
    val tradingApi = new RestTradingApi(httpClient)
    val stocksStorage = new StockStorageImpl()
    val tradingService = new TradingServiceImpl(tradingApi, stocksStorage)
    val controller = new HttpController(tradingService)

    val routes = get {
      pathPrefix("index") {
        getFromResource("index.html")
      } ~ pathPrefix("js") {
        getFromResourceDirectory("js")
      } ~ pathPrefix("css") {
        getFromResourceDirectory("css")
      }
    } ~ controller.routes

    Http().bindAndHandle(routes, "localhost", 8080)
  }

}
