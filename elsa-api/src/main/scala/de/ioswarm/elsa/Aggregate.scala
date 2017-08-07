package de.ioswarm.elsa

/**
  * Created by andreas on 23.04.17.
  */
sealed abstract class Aggregate(val symbol: String)

case object MIN extends Aggregate("min")
case object MAX extends Aggregate("max")
case object SUM extends Aggregate("sum")
case object AVG extends Aggregate("avg")
case object MEDIAN extends Aggregate("median")
