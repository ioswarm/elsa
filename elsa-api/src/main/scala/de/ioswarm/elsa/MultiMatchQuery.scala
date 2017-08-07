package de.ioswarm.elsa

import argonaut.Argonaut._

/**
  * Created by andreas on 30.07.17.
  */
object MultiMatchQuery extends Queries {

  def apply(t: Term): MultiMatchQuery = MultiMatchQuery(t.field.split(",").toSet, t.value)
  def apply(fields: String, values: String*): MultiMatchQuery = MultiMatchQuery(fields.split(",").toSet, values.mkString(" "))
}
case class MultiMatchQuery(fields: Set[String]
                           , value: String
                           , queryType: Option[MultiMatchType] = None
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

  private[elsa] def isExtended: Boolean = queryType.isDefined || operator.isDefined || zeroTerms.isDefined || cutoffFrequency.isDefined

  def queryType(mmt: MultiMatchType): MultiMatchQuery = copy(queryType = Some(mmt))

  def operator(o: Operator): MultiMatchQuery = copy(operator = Option(o))
  def zeroTerms(b: Boolean): MultiMatchQuery = copy(zeroTerms = Option(b))
  def cutoffFrequency(d: Double): MultiMatchQuery = copy(cutoffFrequency = Option(d))

  override def toString: String = this.asJson.spaces2

}

sealed abstract class MultiMatchType(val name: String)

case object MM_BEST_FIELDS extends MultiMatchType("best_fields")
case object MM_MOST_FIELDS extends MultiMatchType("most_fields")
case object MM_CROSS_FIELDS extends MultiMatchType("cross_fields")
case object MM_PHRASE extends MultiMatchType("phrase")
case object MM_PHRASE_PREFIX extends MultiMatchType("phrase_prefix")