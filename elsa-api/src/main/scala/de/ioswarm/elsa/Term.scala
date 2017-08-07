package de.ioswarm.elsa

/**
  * Created by andreas on 30.07.17.
  */
case class Term(field: String, values: String*) {
  def value: String = values.mkString(" ")
}

