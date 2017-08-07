package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 06.08.17.
  */
object StringQuery extends Queries {

  def apply(t: Term): StringQuery = StringQuery(t.value, Some(t.field.split(" ").toSet))

}
case class StringQuery(value: String
                       , fields: Option[Set[String]] = None
                       , defaultField: Option[String] = None
                       , defaultOperator: Option[Operator] = None
                       , analyzer: Option[String] = None
                       , allowLeadingWildcard: Option[Boolean] = None
                       , enablePositionIncrements: Option[Boolean] = None
                       , fuzzMaxExpansions: Option[Int] = None
                       , fuzziness: Option[Fuzziness] = None
                       , fuzzPrefixLength: Option[Int] = None
                       , phraseSlop: Option[Int] = None
                       , boost: Option[Double] = None
                       , autoGeneratePhraseQueries: Option[Boolean] = None
                       , analyzeWildcard: Option[Boolean] = None
                       , maxDeterminizedStates: Option[Int] = None
                       , minimumShouldMatch: Option[String] = None
                       , lenient: Option[Boolean] = None
                       // TODO, timeZone: Option[String] = None
                       , quoteFieldSuffix: Option[String] = None
                       , splitOnWhitespace: Option[Boolean] = None
                       , allFields: Option[Set[String]] = None
                      ) extends FilterQueryType {

  def fields(f: Set[String]): StringQuery = copy(fields = Some(f))
  def field(f: String): StringQuery = copy(fields = if(fields.isDefined) fields.map(fs => fs + f) else Some(Set(f)))

  def defaultField(field: String): StringQuery = copy(defaultField = Some(field))
  def defaultOperator(op: Operator): StringQuery = copy(defaultOperator = Some(op))
  def analyzer(a: String): StringQuery = copy(analyzer = Some(a))
  def allowLeadingWildcard(b: Boolean): StringQuery = copy(allowLeadingWildcard = Some(b))
  def enablePositionIncrements(b: Boolean): StringQuery = copy(enablePositionIncrements = Some(b))
  def fuzzMaxExpansions(i: Int): StringQuery = copy(fuzzMaxExpansions = Some(i))
  def fuzziness(f: Fuzziness): StringQuery = copy(fuzziness = Some(f))
  def fuzzPrefixLength(i: Int): StringQuery = copy(fuzzPrefixLength = Some(i))
  def phraseSlop(i: Int): StringQuery = copy(phraseSlop = Some(i))
  def boost(d: Double): StringQuery = copy(boost = Some(d))
  def autoGeneratePhraseQueries(b: Boolean): StringQuery = copy(autoGeneratePhraseQueries = Some(b))
  def analyzeWildcard(b: Boolean): StringQuery = copy(analyzeWildcard = Some(b))
  def maxDeterminizedStates(i: Int): StringQuery = copy(maxDeterminizedStates = Some(i))
  def minimumShouldMatch(s: String): StringQuery = copy(minimumShouldMatch = Some(s))
  def lenient(b: Boolean): StringQuery = copy(lenient = Some(b))
  def quoteFieldSuffix(s: String): StringQuery = copy(quoteFieldSuffix = Some(s))
  def splitOnWhitespace(b: Boolean): StringQuery = copy(splitOnWhitespace = Some(b))

  def allFields(f: Set[String]): StringQuery = copy(allFields = Some(f))
  def allField(f: String): StringQuery = copy(allFields = if(allFields.isDefined) allFields.map(fs => fs + f) else Some(Set(f)))

  override def toString: String = this.asJson.spaces2

}
