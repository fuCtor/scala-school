package workshop.akka.http

import workshop.akka.http.domain.{Stock, StockInfo, StockPrice, StockPriceInfo}
import workshop.akka.http.trading.TradingApi

import scala.concurrent.{ExecutionContext, Future}

trait TradingService {
  def stocks(): Future[Seq[Stock]]

  def stockInfo(id: String): Future[StockInfo]

  def addStock(id: String): Future[Unit]

  def removeStock(id: String): Future[Unit]

  def stockPricesInfo(): Future[Seq[StockPriceInfo]]
}

class TradingServiceImpl(tradingApi: TradingApi,
                         stocksStorage: StocksStorage)
                        (implicit ec: ExecutionContext)
  extends TradingService {

  override def stocks(): Future[Seq[Stock]] = {
    tradingApi.companies().map(_.map(company => Stock(
      id = company.symbol,
      name = company.name
    )))
  }

  override def stockInfo(id: String): Future[StockInfo] = {
    tradingApi.company(id).map {
      case None =>
        throw new IllegalArgumentException("Unknown stock")
      case Some(company) =>
        StockInfo(
          id = company.symbol,
          name = company.companyName,
          description = company.description
        )
    }
  }

  override def addStock(id: String): Future[Unit] = stocksStorage.add(id).map(_ => ())

  override def removeStock(id: String): Future[Unit] = stocksStorage.remove(id).map(_ => ())

  override def stockPricesInfo(): Future[Seq[StockPriceInfo]] = {
    stocksStorage.getAll().flatMap { stocks =>
      Future.traverse(stocks) { id =>
        val companyF = tradingApi.company(id).map(_.getOrElse(
          throw new IllegalArgumentException
        ))
        val pricesF = tradingApi.prices(id)
        for {
          company <- companyF
          prices <- pricesF
        } yield StockPriceInfo(
          id = company.symbol,
          name = company.companyName,
          prices = prices.map(price => StockPrice(
            label = price.label,
            close = price.close
          ))
        )
      }
    }
  }
}
