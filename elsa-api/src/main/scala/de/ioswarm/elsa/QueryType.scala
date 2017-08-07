package de.ioswarm.elsa

/**
  * Created by andreas on 30.07.17.
  */
trait QueryType {

  def query(directory: Directory): Query = Query(directory,this)

  def query(qry: Query): Query = qry query this

}

trait FilterQueryType extends QueryType {

  def constantScore(boost: Double): ConstantScoreQuery = ConstantScoreQuery(this, boost)
  def CONSTANT_SCORT(boost: Double): ConstantScoreQuery = constantScore(boost)

  def must(queries: QueryType*): BoolQuery = BoolQuery.must(this +: queries :_*)
  def MUST(queries: QueryType*): BoolQuery = must(queries:_*)

  def filter(queries: QueryType*): BoolQuery = BoolQuery.filter(this +: queries :_*)
  def FILTER(queries: QueryType*): BoolQuery = filter(queries:_*)

  def should(queries: QueryType*): BoolQuery = BoolQuery.filter(this +: queries :_*)
  def SHOULD(queries: QueryType*): BoolQuery = should(queries:_*)

  def mustNot(queries: QueryType*): BoolQuery = BoolQuery.mustNot(this +: queries :_*)
  def MUST_NOT(queries: QueryType*): BoolQuery = mustNot(queries:_*)
}

trait CompoundQueryType extends QueryType {

}