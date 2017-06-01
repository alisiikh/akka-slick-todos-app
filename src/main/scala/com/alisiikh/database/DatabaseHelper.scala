package com.alisiikh.database

import com.alisiikh.domain.{Todo, Todos}
import slick.dbio.DBIOAction
import slick.lifted.TableQuery

import slick.jdbc.H2Profile.api._

/**
  * @author alisiikh.
  */
class DatabaseHelper extends DatabaseAccessor {

  private val todos = TableQuery[Todos]

  def initSchema(): Unit = {
    val setup = DBIOAction.seq(
      todos.schema.create
    )

    db.run(setup)
  }

  def createTestData() {
    val inserts = todos ++= Seq(
      Todo(1, "Learn Scala", done = true),
      Todo(2, "Learn Akka", done = true),
      Todo(3, "Buy a car", done = false)
    )

    db.run(inserts)
  }
}
