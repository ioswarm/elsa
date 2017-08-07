package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 30.07.17.
  */
object CommonQuery extends Queries {

  def apply(t: Term): CommonQuery = CommonQuery(t.field, t.value)
}
case class CommonQuery(field: String
                       , value: String
                       , cutoffFrequency: Option[Double] = None
                       , minShouldMatch: Option[Int] = None // TODO high_freq and low_freq
                       , highFreqOperator: Option[Operator] = None
                       , lowFreqOperator: Option[Operator] = None) extends FilterQueryType {
  // TODO boost, analyzer, disable_coord
  private[elsa] def isExtended: Boolean = cutoffFrequency.isDefined || minShouldMatch.isDefined || highFreqOperator.isDefined || lowFreqOperator.isDefined


  def cutoffFrequency(cutofffreq: Double): CommonQuery = copy(cutoffFrequency = Option(cutofffreq))
  def minShouldMatch(minmatch: Int): CommonQuery = copy(minShouldMatch = Option(minmatch))
  def highFreqOperator(highop: Operator): CommonQuery = copy(highFreqOperator = Option(highop))
  def lowFreqOperator(lowop: Operator): CommonQuery = copy(lowFreqOperator = Option(lowop))

  override def toString: JsonField = this.asJson.spaces2
}
