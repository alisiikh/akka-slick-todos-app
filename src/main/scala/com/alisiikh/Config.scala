package com.alisiikh

import com.typesafe.config.{Config, ConfigFactory}


/**
  * @author alisiikh.
  */
object Config {
  lazy val application: Config = ConfigFactory.load("application.conf")
}
