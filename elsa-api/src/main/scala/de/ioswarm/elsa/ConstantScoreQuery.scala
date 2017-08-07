package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 02.08.17.
  */
object ConstantScoreQuery extends Queries {

  def apply(filter: FilterQueryType, boost: Double): ConstantScoreQuery = new ConstantScoreQuery(Seq(filter), boost)
}
case class ConstantScoreQuery(filter: Seq[FilterQueryType], boost: Double) extends CompoundQueryType {

  def filter(f: FilterQueryType): ConstantScoreQuery = copy(filter = this.filter :+ f)

  override def toString: String = this.asJson.spaces2

}
