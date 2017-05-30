package com.alisiikh.web

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.alisiikh.domain.Todo
import com.alisiikh.service.TodoService
import org.slf4j.LoggerFactory

import scala.concurrent.Future
import scala.util.{Failure, Success}


/**
  * @author alisiikh.
  */
object WebServer extends App with JsonSupport  {

  private val LOG = LoggerFactory.getLogger(WebServer.getClass)

  implicit val actorSystem = ActorSystem("system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = actorSystem.dispatcher

  val routes =
    pathPrefix("todos") {
      pathEnd {
        (get & path(Segment)) { id =>
          complete(TodoService.findOne(1))
        } ~
        (post & entity(as[Todo])) { item =>
          ???
        } ~
        (put & entity(as[Todo])) { item =>
          ???
        } ~
        (delete & path(Segment)) { id =>
          ???
        }
      }
    }

  val (host, port) = ("localhost", 8080)
  val bindingFuture = Http()
    .bindAndHandle(routes, host, port)

  bindingFuture.onComplete {
    case Success(_) => LOG.info(s"Server started at http://$host:$port")
    case Failure(ex) => LOG.error("Failed to start server", ex)
  }
}

