package de.ioswarm.elsa

import argonaut._
import Argonaut._

/**
  * Created by andreas on 02.08.17.
  */
trait Queries {

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

  implicit def matchEncodeJson: EncodeJson[MatchQuery] = EncodeJson((mm: MatchQuery) => {
    Json("match" := Json(
      if (mm.isExtended)
        mm.field := Json(
          "query" := mm.value
        ).->?:(
          mm.operator.map(op => "operator" := op.symbol)
        ).->?:(
          mm.zeroTerms.map(zt => "zero_term_query" := (if (zt) "all" else "none"))
        ).->?:(
          mm.cutoffFrequency.map(cof => "cutoff_frequency" := cof)
        )
      else mm.field := mm.value
    ))
  }
  )

  implicit def commonQueryEncodeJson: EncodeJson[CommonQuery] = EncodeJson((c: CommonQuery) => {
    Json("common" := Json(
      if (c.isExtended)
        c.field := Json(
          "query" := c.value
        ).->?:(
          c.cutoffFrequency.map(d => "cutoff_freqency" := d)
        ).->?:(
          c.minShouldMatch.map(i => "minimum_should_match" := i)
        ).->?:(
          c.highFreqOperator.map(op => "high_freq_operator" := op.symbol)
        ).->?:(
          c.lowFreqOperator.map(op => "low_freq_operator" := op.symbol)
        )
      else c.field := c.value
    ))
  })

  implicit def multiMatchEncodeJson: EncodeJson[MultiMatchQuery] = EncodeJson((mm: MultiMatchQuery) => {
    Json("multi_match" := Json(
      "query" := mm.value
      , "fields" := mm.fields
    ).->?:(
      mm.queryType.map(t => "type" := t.name)
    ).->?:(
      mm.operator.map(op => "operator" := op.symbol)
    ).->?:(
      mm.zeroTerms.map(zt => "zero_term_query" := (if (zt) "all" else "none"))
    ).->?:(
      mm.cutoffFrequency.map(cof => "cutoff_frequency" := cof)
    ))
  })

  implicit def existsQueryEncodeJson: EncodeJson[ExistsQuery] = EncodeJson((e: ExistsQuery) => {
    Json(
      "exists" := Json("field" := e.field)
    )
  })

  implicit def matchPhrasePrefixEncodeJson: EncodeJson[MatchPhrasePrefixQuery] = EncodeJson((mp: MatchPhrasePrefixQuery) => {
    Json("match_phrase_prefix" := Json(
      if (mp.isExtended)
        mp.field := Json(
          "query" := mp.value
        ).->?:(
          mp.maxExpansions.map(m => "max_expansions" := m)
        )
      else mp.field := mp.value
    ))
  })

  implicit def matchPhraseEncodeJson: EncodeJson[MatchPhraseQuery] = EncodeJson((mp: MatchPhraseQuery) => {
    Json("match_phrase" := Json(
      if (mp.isExtended)
        mp.field := Json(
          "query" := mp.value
        ).->?:(
          mp.slop.map(s => "slop" := s)
        ).->?:(
          mp.analyzer.map(a => "analyzer" := a)
        )
      else mp.field := mp.value
    ))
  })

  implicit def prefixQueryEncodeJson: EncodeJson[PrefixQuery] = EncodeJson((p: PrefixQuery) => {
    Json(
      "prefix" := Json(
        if (p.isExtended)
          p.field := Json(
            "value" := p.prefix
          ).->?:(
            p.boost.map(d => "boost" := d)
          )
        else p.field := p.prefix
      )
    )
  })

  private implicit def anyEncodeJson: EncodeJson[Any] = EncodeJson(
    (a: Any) => {
      a match {
        case i: Int => jNumber(i)
        case l: Long => jNumber(l)
        case d: Double => jNumber(d)
        case s: String => jString(s)
        case _ => jNull
      }
    }
  )

  implicit def rangeQueryEncodeJson: EncodeJson[RangeQuery] = EncodeJson((r: RangeQuery) => {
    Json("range" := Json(
      r.field := Json().->?:(
        r.gt.map(a => "gt" := a)
      ).->?:(
        r.gte.map(a => "gte" := a)
      ).->?:(
        r.lt.map(a => "lt" := a)
      ).->?:(
        r.lte.map(a => "lte" := a)
      ).->?:(
        r.timeZone.map(tz => "time_zone" := tz)
      ).->?:(
        r.boost.map(b => "boost" := b)
      ).->?:(
        r.format.map(f => "format" := f)
      )
    ))
  })

  implicit def simpelStringEncodeJson: EncodeJson[SimpleStringQuery] = EncodeJson((sq: SimpleStringQuery) => {
    Json("query_string" := Json(
      "query" := sq.value
    ).->?:(
      sq.fields.map(f => "fields" := f)
    ).->?:(
      sq.defaultOperator.map(op => "default_operator" := op.symbol.toUpperCase)
    ).->?:(
      sq.analyzer.map(a => "analyzer" := a)
    ).->?:(
      sq.analyzeWildcard.map(f => "analyze_wildcard" := f)
    ).->?:(
      sq.minimumShouldMatch.map(f => "minimum_should_match" := f)
    ).->?:(
      sq.lenient.map(f => "lenient" := f)
    ).->?:(
      sq.quoteFieldSuffix.map(f => "quote_field_suffix" := f)
    ).->?:(
      sq.allFields.map(f => "all_fields" := f)
    ))
  })

  implicit def stringQueryEncodeJson: EncodeJson[StringQuery] = EncodeJson((sq: StringQuery) => {
    Json("query_string" := Json(
      "query" := sq.value
    ).->?:(
      sq.fields.map(f => "fields" := f)
    ).->?:(
      sq.defaultField.map(f => "default_field" := f)
    ).->?:(
      sq.defaultOperator.map(op => "default_operator" := op.symbol.toUpperCase)
    ).->?:(
      sq.analyzer.map(a => "analyzer" := a)
    ).->?:(
      sq.allowLeadingWildcard.map(b => "allow_leading_wildcard" := b)
    ).->?:(
      sq.enablePositionIncrements.map(f => "enable_position_increments" := f)
    ).->?:(
      sq.fuzzMaxExpansions.map(f => "fuzz_max_expansions" := f)
    ).->?:(
      sq.fuzziness.map(f => "fuzziness" := f.value)
    ).->?:(
      sq.fuzzPrefixLength.map(f => "fuzz_prefix_length" := f)
    ).->?:(
      sq.phraseSlop.map(f => "phrase_slop" := f)
    ).->?:(
      sq.boost.map(f => "boost" := f)
    ).->?:(
      sq.autoGeneratePhraseQueries.map(f => "auto_generate_phrase_queries" := f)
    ).->?:(
      sq.analyzeWildcard.map(f => "analyze_wildcard" := f)
    ).->?:(
      sq.maxDeterminizedStates.map(f => "max_determinized_states" := f)
    ).->?:(
      sq.minimumShouldMatch.map(f => "minimum_should_match" := f)
    ).->?:(
      sq.lenient.map(f => "lenient" := f)
    ).->?:(
      sq.quoteFieldSuffix.map(f => "quote_field_suffix" := f)
    ).->?:(
      sq.splitOnWhitespace.map(f => "split_on_whitespace" := f)
    ).->?:(
      sq.allFields.map(f => "all_fields" := f)
    ))
  })

  implicit def wildcardQueryEncodeJson: EncodeJson[WildcardQuery] = EncodeJson((p: WildcardQuery) => {
    Json(
      "prefix" := Json(
        if (p.isExtended)
          p.field := Json(
            "value" := p.wildcard
          ).->?:(
            p.boost.map(d => "boost" := d)
          )
        else p.field := p.wildcard
      )
    )
  })


  implicit def constantScoreQueryEncodeJson: EncodeJson[ConstantScoreQuery] = EncodeJson((c: ConstantScoreQuery) => {
    Json(
      if (c.filter.size == 1)
        "filter" := c.filter.head
      else "filter" := jArray(c.filter.map(f => f.asJson).toList),
      "boost" := c.boost
    )
  })

  implicit def boolQueryEncodeJson: EncodeJson[BoolQuery] = EncodeJson((b: BoolQuery) => {
    Json(
      "bool" := Json().->?:(
        b.must.map(q => "must" := jArray(q.map(_.asJson).toList))
      ).->?:(
        b.filter.map(q => "filter" := jArray(q.map(_.asJson).toList))
      ).->?:(
        b.should.map(q => "should" := jArray(q.map(_.asJson).toList))
      ).->?:(
        b.mustNot.map(q => "must_not" := jArray(q.map(_.asJson).toList))
      ).->?:(
        b.boost.map(d => "boost" := d)
      ).->?:(
        b.minimumShouldMatch.map(i => "minimum_should_match" := i)
      ).->?:(
        b.disableCoord.map(b => "disable_coord" := b)
      )
    )
  })

  implicit def queryTypeEncodeJson[T <:QueryType]: EncodeJson[T] = EncodeJson((qt: T) => {
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

      case c: ConstantScoreQuery => c.asJson
      case b: BoolQuery => b.asJson

      case _ => Json()
    }
  })

}
