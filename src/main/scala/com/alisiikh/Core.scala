package com.alisiikh

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext

/**
  * @author alisiikh.
  */
trait Core {
  def actorSystem: ActorSystem
  def materializer: ActorMaterializer
  def executionContext: ExecutionContext
}

trait BootedCore extends Core {
  implicit val actorSystem = ActorSystem("system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = actorSystem.dispatcher
}
