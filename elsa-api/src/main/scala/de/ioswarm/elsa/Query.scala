package de.ioswarm.elsa

import argonaut._
import Argonaut._
import de.ioswarm.elsa.Query.NoQuery

/**
  * Created by andreas on 06.08.17.
  */
object Query extends Queries {

  implicit def queryEncodeJson: EncodeJson[Query] = EncodeJson((q: Query) => {
    Json(
      "query" := q.query
    ).->?:(
      q.first.map(f => "first" := f)
    ).->?:(
      q.size.map(s => "size" := s)
    )
    // TODO sort
  })

  case object NoQuery extends QueryType
}
case class Query(directory: Directory
                 , query: QueryType = NoQuery
                 , first: Option[Int] = None
                 , size: Option[Int] = None
                 , sort: Seq[SortDirection] = Seq.empty[SortDirection]
                 ) {

  def first(v: Int): Query = copy(first = Some(v))
  def size(v: Int): Query = copy(size = Some(v))

  def query(q: QueryType): Query = copy(query = q)

  def commandDir: Directory = directory / "_search"

  def print(): Unit = {
    println(commandDir)
    println(toString)
  }

  override def toString: String = this.asJson.spaces2

}
