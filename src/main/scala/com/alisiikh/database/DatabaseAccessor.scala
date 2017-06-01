package com.alisiikh.database

/**
  * @author alisiikh.
  */
trait DatabaseAccessor {
  import slick.jdbc.H2Profile.api._

  lazy val db: Database = Database.forConfig("h2Database")
}
