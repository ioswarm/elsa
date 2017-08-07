package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 06.08.17.
  */
object MatchPhraseQuery extends Queries {

  def apply(term: Term): MatchPhraseQuery = MatchPhraseQuery(term.field, term.value)
}
case class MatchPhraseQuery(field: String, value: String, slop: Option[Int] = None, analyzer: Option[String] = None) extends FilterQueryType {
  private[elsa] def isExtended: Boolean = slop.isDefined || analyzer.isDefined

  def slop(i: Int): MatchPhraseQuery = copy(slop = Some(i))
  def analyzer(a: String): MatchPhraseQuery = copy(analyzer = Some(a))

  override def toString: String = this.asJson.toString
}
