package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 06.08.17.
  */
object PrefixQuery extends Queries {

  def apply(t: Term): PrefixQuery = PrefixQuery(t.field, t.value)
}
case class PrefixQuery(field: String, prefix: String, boost: Option[Double] = None) extends FilterQueryType {

  private[elsa] def isExtended: Boolean = boost.isDefined

  def boost(d: Double): PrefixQuery = copy(boost = Some(d))

  override def toString: String = this.asJson.spaces2

}
