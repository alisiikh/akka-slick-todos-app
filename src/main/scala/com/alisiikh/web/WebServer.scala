package com.alisiikh.web

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.alisiikh.database.DatabaseHelper
import com.alisiikh.domain.Todo
import com.alisiikh.service.TodoService
import org.slf4j.LoggerFactory

import scala.concurrent.Future
import scala.util.{Failure, Success}


/**
  * @author alisiikh.
  */
object WebServer extends App with JsonSupport {

  private val LOG = LoggerFactory.getLogger(WebServer.getClass)

  implicit val actorSystem = ActorSystem("system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = actorSystem.dispatcher

  val routes =
    pathPrefix("todos") {
      path(LongNumber) { id =>
        get {
          complete(TodoService.findOne(id))
        } ~
        delete {
          TodoService.delete(id)

          complete(StatusCodes.NoContent)
        }
      } ~
        get {
          complete(TodoService.findAll())
        } ~
        (post & entity(as[Todo])) { todo =>
          TodoService.saveOrUpdate(todo)
          complete(todo)
        } ~
        (put & entity(as[Todo])) { todo =>
          ???
        }
    }

  val (host, port) = ("localhost", 8080)
  val bindingFuture = Http()
    .bindAndHandle(routes, host, port)

  bindingFuture.onComplete {
    case Success(_) => {
      LOG.info(s"Server started at http://$host:$port")

      LOG.info("Populating database with schema and test data")
      DatabaseHelper.populateDatabase()

      LOG.info("Success, now server is ready to work")
    }
    case Failure(ex) => LOG.error("Failed to start server", ex)
  }
}

