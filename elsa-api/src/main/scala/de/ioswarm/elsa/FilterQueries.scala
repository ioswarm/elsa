package de.ioswarm.elsa

import argonaut.Argonaut._
import argonaut._

/**
  * Created by andreas on 05.08.17.
  */
trait FilterQueries {



  implicit def termQueryEncodeJson: EncodeJson[TermQuery] = EncodeJson((tq: TermQuery) => {
    Json("term" := Json(
      if (tq.isExtended)
        tq.field := Json(
          "value" := tq.value
        ).->?:(
          tq.boost.map(d => "boost" := d)
        )
      else tq.field := tq.value
    ))
  })

  /*implicit def filterQueryTypeEncodeJson[T <:FilterQueryType]: EncodeJson[T] = EncodeJson((qt: T) => {
    qt match {
      case mm: MatchQuery => mm.asJson
      case mp: MatchPhraseQuery => mp.asJson
      case mp: MatchPhrasePrefixQuery => mp.asJson
      case mm: MultiMatchQuery => mm.asJson
      case c: CommonQuery => c.asJson
      case s: StringQuery => s.asJson
      case s: SimpleStringQuery => s.asJson
      case t: TermQuery => t.asJson
      case r: RangeQuery => r.asJson
      case e: ExistsQuery => e.asJson
      case p: PrefixQuery => p.asJson
      case w: WildcardQuery => w.asJson
      case _ => Json()
    }
  })*/

}
