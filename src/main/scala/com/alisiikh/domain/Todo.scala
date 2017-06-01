package com.alisiikh.domain

import slick.jdbc.H2Profile.api._
import slick.lifted.ProvenShape

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * @author alisiikh.
  */
class Todos(tag: Tag) extends Table[Todo](tag, "todos") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def desc = column[String]("description")
  def done = column[Boolean]("done")

  def * : ProvenShape[Todo] = (id, desc, done) <> (Todo.tupled, Todo.unapply)
}

case class Todo(id: Long, desc: String, done: Boolean)