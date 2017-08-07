package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 06.08.17.
  */
object ExistsQuery extends Queries {

  def apply(term: Term): ExistsQuery = ExistsQuery(term.field)
}
case class ExistsQuery(field: String) extends FilterQueryType {
  override def toString: String = this.asJson.spaces2
}
