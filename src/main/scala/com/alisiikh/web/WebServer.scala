package com.alisiikh.web

import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import com.alisiikh.database.DatabaseHelper
import com.alisiikh.{BootedCore, Config}
import com.alisiikh.domain.Todo
import com.alisiikh.service.TodoService
import org.slf4j.LoggerFactory

import scala.util.{Failure, Success}


/**
  * @author alisiikh.
  */
object WebServer extends App with BootedCore with JsonSupport {

  private val LOG = LoggerFactory.getLogger(WebServer.getClass)

  val todoService = new TodoService
  val databaseHelper = new DatabaseHelper

  val routes = pathPrefix("todos") {
      path(LongNumber) { id =>
        get {
          complete(todoService.findOne(id))
        } ~
        delete {
          todoService.delete(id)

          complete(StatusCodes.NoContent)
        }
      } ~
        get {
          complete(todoService.findAll())
        } ~
        (put & entity(as[Todo])) { todo =>
          todoService.insert(todo)
          complete(todo)
        } ~
        (post & entity(as[Todo])) { todo =>
          todoService.update(todo)
          complete(todo)
        }
    }

  val config = Config.application.getConfig("server")
  val (host, port) = (config.getString("host"), config.getInt("port"))

  Http().bindAndHandle(routes, host, port).onComplete {
    case Success(_) =>
      LOG.info(s"Server started at http://$host:$port")

      LOG.info("Populating database with schema and test data")
      databaseHelper.initSchema()
      databaseHelper.createTestData()

      LOG.info("Success, now server is ready to work")
    case Failure(ex) => LOG.error("Failed to start server", ex)
  }
}

