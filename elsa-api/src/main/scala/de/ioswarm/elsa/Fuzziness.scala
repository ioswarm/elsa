package de.ioswarm.elsa

/**
  * Created by andreas on 30.07.17.
  */
sealed abstract class Fuzziness(val value: String)
case object ZERO extends Fuzziness("0")
case object ONE extends Fuzziness("1")
case object TWO extends Fuzziness("2")
case object AUTO extends Fuzziness("auto")
