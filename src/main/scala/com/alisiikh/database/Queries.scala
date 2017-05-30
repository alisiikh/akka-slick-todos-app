package com.alisiikh.database

import com.alisiikh.domain.TodoTable
import slick.lifted.TableQuery

/**
  * @author alisiikh.
  */
object Queries {
  val todos = TableQuery[TodoTable]
}
