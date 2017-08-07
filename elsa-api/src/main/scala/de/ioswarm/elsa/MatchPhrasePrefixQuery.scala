package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 30.07.17.
  */
object MatchPhrasePrefixQuery extends Queries {

  def apply(t: Term): MatchPhrasePrefixQuery = MatchPhrasePrefixQuery(t.field, t.value)
}
case class MatchPhrasePrefixQuery(field: String, value: String, maxExpansions: Option[Int] = None) extends FilterQueryType {
  private[elsa] def isExtended: Boolean = maxExpansions.isDefined

  def maxExpansions(i: Int): MatchPhrasePrefixQuery = copy(maxExpansions = Some(i))

  override def toString: String = this.asJson.spaces2

}
