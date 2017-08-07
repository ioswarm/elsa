package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 06.08.17.
  */
object WildcardQuery extends Queries {

  def apply(t: Term): WildcardQuery = WildcardQuery(t.field, t.value)

}
case class WildcardQuery(field: String, wildcard: String, boost: Option[Double] = None) extends FilterQueryType {

  private[elsa] def isExtended: Boolean = boost.isDefined

  def boost(d: Double): WildcardQuery = copy(boost = Some(d))

  override def toString: String = this.asJson.spaces2

}
