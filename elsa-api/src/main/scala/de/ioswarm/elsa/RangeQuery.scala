package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 06.08.17.
  */
object RangeQuery extends Queries {

  def apply(t: Term): RangeQuery = RangeQuery(t.field)

}
case class RangeQuery(field: String
                      , gt: Option[Any] = None
                      , gte: Option[Any] = None
                      , lt: Option[Any] = None
                      , lte: Option[Any] = None
                      , timeZone: Option[String] = None
                      , boost: Option[Double] = None
                      , format: Option[String] = None
                     ) extends FilterQueryType {

  def boost(d: Double): RangeQuery = copy(boost = Some(d))
  def format(s: String): RangeQuery = copy(format = Some(s))
  def timeZone(s: String): RangeQuery = copy(timeZone = Some(s))

  def gt(a: Any): RangeQuery = copy(gt = Some(a))
  def gte(a: Any): RangeQuery = copy(gte = Some(a))
  def lt(a: Any): RangeQuery = copy(lt = Some(a))
  def lte(a: Any): RangeQuery = copy(lte = Some(a))

  def >(a: Any): RangeQuery = gt(a)
  def >=(a: Any): RangeQuery = gte(a)
  def <(a: Any): RangeQuery = lt(a)
  def <=(a: Any): RangeQuery = lte(a)

  override def toString: String = this.asJson.spaces2

}
