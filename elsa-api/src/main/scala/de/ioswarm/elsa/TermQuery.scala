package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 30.07.17.
  */
object TermQuery extends Queries {

  def apply(t: Term): TermQuery = TermQuery(t.field, t.value)
}
case class TermQuery(field: String, value: String, boost: Option[Double] = None) extends FilterQueryType {

  private[elsa] def isExtended: Boolean = boost.isDefined

  def boost(d: Double): TermQuery = copy(boost = Some(d))

  override def toString: JsonField = this.asJson.spaces2

}
