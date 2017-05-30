package com.alisiikh.web

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.alisiikh.domain.Todo
import spray.json.{DefaultJsonProtocol, PrettyPrinter}

/**
  * @author alisiikh.
  */
trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val printer = PrettyPrinter
  implicit val todoFormat = jsonFormat3(Todo)
}
