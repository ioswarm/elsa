package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 05.08.17.
  */
object BoolQuery extends Queries {

  def apply(query: BoolQueryType): BoolQuery = query match {
    case m: MUST => must(m.queries:_*)
    case f: FILTER => filter(f.queries:_*)
    case s: SHOULD => should(s.queries:_*)
    case n: MUST_NOT => mustNot(n.queries:_*)
    case _ => BoolQuery()
  }

  def must(queries: QueryType*): BoolQuery = BoolQuery(must = if (queries.nonEmpty) Some(queries) else None)
  def filter(queries: QueryType*): BoolQuery = BoolQuery(filter = if (queries.nonEmpty) Some(queries) else None)
  def should(queries: QueryType*): BoolQuery = BoolQuery(should = if (queries.nonEmpty) Some(queries) else None)
  def mustNot(queries: QueryType*): BoolQuery = BoolQuery(mustNot = if (queries.nonEmpty) Some(queries) else None)
}
case class BoolQuery(must: Option[Seq[QueryType]] = None
                     , filter: Option[Seq[QueryType]] = None
                     , should: Option[Seq[QueryType]] = None
                     , mustNot:Option[Seq[QueryType]] = None
                     , boost: Option[Double] = None
                     , minimumShouldMatch: Option[Int] = None
                     , disableCoord: Option[Boolean] = None) extends CompoundQueryType {

  def must(q: QueryType*): BoolQuery = copy(must = Some(this.must.getOrElse(Seq.empty[QueryType]) ++ q))
  def filter(q: QueryType*): BoolQuery = copy(filter = Some(this.filter.getOrElse(Seq.empty[QueryType]) ++ q))
  def should(q: QueryType*): BoolQuery = copy(should = Some(this.should.getOrElse(Seq.empty[QueryType]) ++ q))
  def mustNot(q: QueryType*): BoolQuery = copy(mustNot = Some(this.mustNot.getOrElse(Seq.empty[QueryType]) ++ q))

  def append(query: BoolQueryType): BoolQuery = query match {
    case m: MUST => must(m.queries:_*)
    case f: FILTER => filter(f.queries:_*)
    case s: SHOULD => should(s.queries:_*)
    case n: MUST_NOT => mustNot(n.queries:_*)
    case _ => this
  }

  def boost(b: Double): BoolQuery = copy(boost = Some(b))
  def minimumShouldMatch(i: Int): BoolQuery = copy(minimumShouldMatch = Some(i))
  def disableCoord(b: Boolean): BoolQuery = copy(disableCoord = Some(b))

  override def toString: String = this.asJson.spaces2

}

sealed trait BoolQueryType {
  def queries: Seq[QueryType]
}
case class MUST(queries: QueryType*) extends BoolQueryType
case class FILTER(queries: QueryType*) extends BoolQueryType
case class SHOULD(queries: QueryType*) extends BoolQueryType
case class MUST_NOT(queries: QueryType*) extends BoolQueryType