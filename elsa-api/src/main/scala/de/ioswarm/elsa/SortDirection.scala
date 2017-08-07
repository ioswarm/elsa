package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 23.04.17.
  */
object SortDirection {
  implicit def sortDirectionEncodeJson: EncodeJson[SortDirection] = EncodeJson(
    (sd: SortDirection) => {
      (sd.field := Json(
        "order" := sd.order.symbol
      ).->?:(
        sd.mode.map(m => "mode" := m.symbol)
      ).->?:(
        sd.missing.map(p => "missing" := p.symbol)
      ).->?:(
        sd.nestedPath.map(p => "nested_path" := p)
      )
      ) ->: jEmptyObject
    }
  )
}
case class SortDirection(field: String
                         , order: Order = ASC
                         , mode: Option[Aggregate] = None
                         , missing: Option[Position] = None
                         , nestedPath: Option[String] = None) {
// TODO nested_filter, geo and other
  def order(o: Order): SortDirection = copy(order = o)
  def mode(m: Aggregate): SortDirection = copy(mode = Option(m))
  def missing(p: Position): SortDirection = copy(missing = Option(p))

  def nestedPath(p: String): SortDirection = copy(nestedPath = Option(p))
}

sealed abstract class Order(val symbol: String)
case object ASC extends Order("asc")
case object DESC extends Order("desc")