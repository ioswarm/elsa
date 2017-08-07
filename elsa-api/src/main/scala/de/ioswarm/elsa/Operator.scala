package de.ioswarm.elsa

/**
  * Created by andreas on 18.04.17.
  */
sealed abstract class Operator(val symbol: String)

case object AND extends Operator("and")
case object OR extends Operator("or")
