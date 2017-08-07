package de.ioswarm.elsa

/**
  * Created by andreas on 23.04.17.
  */
sealed abstract class Position(val symbol: String)

case object FIRST extends Position("_first")
case object LAST extends Position("_last")