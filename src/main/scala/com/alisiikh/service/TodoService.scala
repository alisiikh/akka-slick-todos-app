package com.alisiikh.service

import com.alisiikh.database.DatabaseAccessor
import com.alisiikh.domain.{Todo, Todos}
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * @author alisiikh.
  */
class TodoService extends DatabaseAccessor {

  private val todos = TableQuery[Todos]

  def exists(id: Long): Future[Boolean] = db.run(todos.filter(_.id === id).exists.result)

  def findOne(id: Long): Future[Todo] = db.run(todos.filter(_.id === id).result.headOption).map {
      case Some(t) => t
      case None => throw new IllegalArgumentException
    }

  def findAll(): Future[Seq[Todo]] = db.run(todos.result)

  def delete(id: Long): Unit = db.run(todos.filter(_.id === id).delete)

  def insert(todo: Todo) = db.run(todos += todo)

  def update(todo: Todo) = {
    val query = for (t <- todos if t.id === todo.id)
      yield (t.desc, t.done)
    db.run(query.update(todo.desc, todo.done)).map(_ > 0)
  }
}
