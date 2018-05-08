package workshop.akka.http.trading

import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest}
import workshop.akka.http.HttpClient
import workshop.akka.http.trading.domain.{Company, CompanyDescription, CompanyPrices}
import org.json4s._
import org.json4s.jackson.JsonMethods._

import scala.concurrent.{ExecutionContext, Future}

trait TradingApi {
  def companies(): Future[Seq[Company]]

  def company(companyId: String): Future[Option[CompanyDescription]]

  def prices(companyId: String): Future[Seq[CompanyPrices]]
}

class RestTradingApi(httpClient: HttpClient)
                    (implicit ec: ExecutionContext)
  extends TradingApi {

  implicit val formats = DefaultFormats

  override def companies(): Future[Seq[Company]] = {
    val request: HttpRequest = HttpRequest(
      method = HttpMethods.GET,
      uri = "https://api.iextrading.com/1.0/ref-data/symbols"
    )

    httpClient.send(request).map { response =>
      parse(response).extract[Seq[Company]]
    }
  }

  override def company(companyId: String): Future[Option[CompanyDescription]] = {
    val request: HttpRequest = HttpRequest(
      method = HttpMethods.GET,
      uri = s"https://api.iextrading.com/1.0/stock/$companyId/company"
    )

    httpClient.send(request).map { response =>
      parse(response).extract[Option[CompanyDescription]]
    }.recover {
      case _ =>
        None
    }
  }

  override def prices(companyId: String): Future[Seq[CompanyPrices]] = {
    val request: HttpRequest = HttpRequest(
      method = HttpMethods.GET,
      uri = s"https://api.iextrading.com/1.0/stock/$companyId/chart"
    )

    httpClient.send(request).map { response =>
      parse(response).extract[Seq[CompanyPrices]]
    }.recover {
      case _ =>
        Seq.empty
    }
  }
}