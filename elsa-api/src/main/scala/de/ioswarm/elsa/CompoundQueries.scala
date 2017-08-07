package de.ioswarm.elsa

import argonaut.Argonaut._
import argonaut._

/**
  * Created by andreas on 05.08.17.
  */
trait CompoundQueries {

  implicit def compoundQueryTypeEncodeJson[T <:CompoundQueryType]: EncodeJson[T] = EncodeJson((qt: T) => {
    qt match {
      case c: ConstantScoreQuery => c.asJson

      case _ => Json()
    }
  })

}
