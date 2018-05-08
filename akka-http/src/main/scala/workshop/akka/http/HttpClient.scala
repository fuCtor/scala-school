package workshop.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.concurrent.duration._

trait HttpClient {
  def send(request: HttpRequest): Future[String]
}

class AkkaHttpClient(implicit actorSystem: ActorSystem) extends HttpClient {
  import actorSystem.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  val http = Http()

  override def send(request: HttpRequest): Future[String] = {
    http.singleRequest(request).flatMap { response =>
      if(response.status.isFailure()) {
        throw new Exception
      } else {
        response.entity.toStrict(30.seconds).map { entity =>
          entity.data.utf8String
        }
      }
    }
  }
}
