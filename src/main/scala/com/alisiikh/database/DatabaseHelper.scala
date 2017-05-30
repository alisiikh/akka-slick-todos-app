package com.alisiikh.database

import slick.jdbc.H2Profile.api._

/**
  * @author alisiikh.
  */
object DatabaseHelper {
  val db = Database.forConfig("h2Database")

  val todos = Queries.todos

  def populateDatabase(): Unit = {
    val setup = DBIO.seq(
      // Create schema
      todos.schema.create,

      todos ++= Seq(
        (1, "Learn Scala", true),
        (2, "Learn Akka", true),
        (3, "Buy a car", false)
      )
    )

    db.run(setup)
  }
}
