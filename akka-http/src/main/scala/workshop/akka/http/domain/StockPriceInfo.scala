package workshop.akka.http.domain

case class StockPriceInfo(id: String, name: String, prices: Seq[StockPrice])

case class StockPrice(label: String, close: BigDecimal)
