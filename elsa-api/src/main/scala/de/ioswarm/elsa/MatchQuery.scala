package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 06.08.17.
  */
object MatchQuery extends Queries {

  def apply(t: Term): MatchQuery = MatchQuery(t.field, t.value)
}
case class MatchQuery(field: String
                      , value: String
                      // TODO implement fuzziness
                      // TODO implement boost
                      // TODO implement minimum_should_match
                      // TODO implement lenient
                      // TODO implement prefix_length
                      // TODO implement max_expansions
                      // TODO implement rewrite
                      , operator: Option[Operator] = None
                      , zeroTerms: Option[Boolean] = None
                      , cutoffFrequency: Option[Double] = None
                ) extends FilterQueryType {
  private[elsa] def isExtended: Boolean = operator.isDefined || zeroTerms.isDefined || cutoffFrequency.isDefined

  def operator(o: Operator): MatchQuery = copy(operator = Option(o))
  def zeroTerms(b: Boolean): MatchQuery = copy(zeroTerms = Option(b))
  def cutoffFrequency(d: Double): MatchQuery = copy(cutoffFrequency = Option(d))

  override def toString: String = this.asJson.spaces2

}
