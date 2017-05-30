package com.alisiikh.service

import com.alisiikh.database.{DatabaseHelper, Queries}
import com.alisiikh.domain.Todo
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * @author alisiikh.
  */
object TodoService {

  private val db = DatabaseHelper.db
  private val todos = Queries.todos

  def exists(id: Long): Future[Boolean] = db.run(todos.filter(_.id === id).exists.result)

  def findOne(id: Long): Future[Todo] = db.run(todos.filter(_.id === id).result.headOption).map {
      case Some(t) => fromTuple(t)
      case None => throw new IllegalArgumentException
    }

  def findAll(): Future[Seq[Todo]] = db.run(todos.result).map(_.map(t => fromTuple(t)))

  def delete(id: Long): Unit = db.run(todos.filter(_.id === id).delete)

  def saveOrUpdate(todo: Todo) = db.run(todos.insertOrUpdate(todo.id, todo.desc, todo.done))

  private def fromTuple(tuple: (Long, String, Boolean)): Todo = Todo(tuple._1, tuple._2, tuple._3)
}
