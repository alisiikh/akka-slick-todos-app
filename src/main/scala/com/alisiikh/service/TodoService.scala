package com.alisiikh.service

import com.alisiikh.database.{DatabaseHelper, Queries}
import com.alisiikh.domain.Todo
import slick.jdbc.H2Profile.api._

import scala.concurrent.Future

/**
  * @author alisiikh.
  */
object TodoService {

  private val db = DatabaseHelper.db
  private val todos = Queries.todos

  def exists(id: Long): Future[Boolean] = {
    db.run(todos.filter(_.id === id).exists.result)
  }

  def findOne(id: Long): Future[Todo] = ???
  def findAll(): Future[List[Todo]] = ???
}
