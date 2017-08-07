package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 30.07.17.
  */
object SimpleStringQuery extends Queries {

  def apply(t: Term): SimpleStringQuery = {
    if (t.field.split(",").length > 0) SimpleStringQuery(t.value, fields = Some(t.field.split(",").toSet))
    else SimpleStringQuery(t.value)
  }
}
case class SimpleStringQuery(value: String
                             , fields: Option[Set[String]] = None
                             , defaultOperator: Option[Operator] = None
                             , analyzer: Option[String] = None
                             // TODO implement flags
                             , analyzeWildcard: Option[Boolean] = None
                             , minimumShouldMatch: Option[String] = None
                             , lenient: Option[Boolean] = None
                             // TODO, timeZone: Option[String] = None
                             , quoteFieldSuffix: Option[String] = None
                             , allFields: Option[Set[String]] = None
                       ) extends FilterQueryType {

  def fields(f: Set[String]): SimpleStringQuery = copy(fields = Some(f))
  def field(f: String): SimpleStringQuery = copy(fields = if(fields.isDefined) fields.map(fs => fs + f) else Some(Set(f)))

  def defaultOperator(op: Operator): SimpleStringQuery = copy(defaultOperator = Some(op))
  def analyzer(a: String): SimpleStringQuery = copy(analyzer = Some(a))
  def analyzeWildcard(b: Boolean): SimpleStringQuery = copy(analyzeWildcard = Some(b))
  def minimumShouldMatch(s: String): SimpleStringQuery = copy(minimumShouldMatch = Some(s))
  def lenient(b: Boolean): SimpleStringQuery = copy(lenient = Some(b))
  def quoteFieldSuffix(s: String): SimpleStringQuery = copy(quoteFieldSuffix = Some(s))

  def allFields(f: Set[String]): SimpleStringQuery = copy(allFields = Some(f))
  def allField(f: String): SimpleStringQuery = copy(allFields = if(allFields.isDefined) allFields.map(fs => fs + f) else Some(Set(f)))

  override def toString: JsonField = this.asJson.spaces2

}
