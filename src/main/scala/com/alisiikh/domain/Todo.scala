package com.alisiikh.domain

import slick.jdbc.H2Profile.api._
import slick.lifted.ProvenShape

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * @author alisiikh.
  */
class TodoTable(tag: Tag) extends Table[(Long, String, Boolean)](tag, "todos") {
  def id = column[Long]("id", O.PrimaryKey)
  def desc = column[String]("description")
  def done = column[Boolean]("done")

  def * : ProvenShape[(Long, String, Boolean)] = (id, desc, done)
}

case class Todo(id: Long, desc: String, done: Boolean)