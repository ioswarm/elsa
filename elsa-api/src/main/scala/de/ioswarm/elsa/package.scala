package de.ioswarm

/**
  * Created by andreas on 29.07.17.
  */
package object elsa {

//  implicit def tuple2ToDirectory(t: (String, String)): Directory = Directory(t._1, t._2)
  implicit def tuple2ToTerm(t: (String, String)): Term = Term(t._1, t._2)

  implicit def matchQuery(term: Term): MatchQuery = MatchQuery(term)
  def MATCH(term: Term): MatchQuery = matchQuery(term)
  implicit def phrase(term: Term): MatchPhraseQuery = MatchPhraseQuery(term)
  def PHRASE(term: Term): MatchPhraseQuery = phrase(term)
  implicit def phrasePrefix(term: Term): MatchPhrasePrefixQuery = MatchPhrasePrefixQuery(term)
  def PHRASE_PREFIX(term: Term): MatchPhrasePrefixQuery = phrasePrefix(term)
  implicit def multiMatch(term: Term): MultiMatchQuery = MultiMatchQuery(term)
  def MULTI_MATCH(term: Term): MultiMatchQuery = multiMatch(term)
  implicit def common(term: Term): CommonQuery = CommonQuery(term)
  def COMMON(term: Term): CommonQuery = common(term)
  implicit def string(term: Term): StringQuery = StringQuery(term)
  def STRING(term: Term): StringQuery = string(term)
  implicit def simple(term: Term): SimpleStringQuery = SimpleStringQuery(term)
  def SIMPLE(term: Term): SimpleStringQuery = simple(term)
  implicit def term(term: Term): TermQuery = TermQuery(term)
  def TERM(t: Term): TermQuery = term(t)
  implicit def range(term: Term): RangeQuery = RangeQuery(term)
  def RANGE(term: Term): RangeQuery = range(term)
  implicit def exists(term: Term): ExistsQuery = ExistsQuery(term)
  def EXISTS(term: Term): ExistsQuery = exists(term)
  implicit def prefix(term: Term): PrefixQuery = PrefixQuery(term)
  def PREFIX(term: Term): PrefixQuery = prefix(term)
  implicit def wildcard(term: Term): WildcardQuery = WildcardQuery(term)
  def WILDCARD(term: Term): WildcardQuery = wildcard(term)


  implicit def string(s: String): StringQuery = StringQuery(s)
  def STRING(s: String): StringQuery = string(s)
  implicit def simple(s: String): SimpleStringQuery = SimpleStringQuery(s)
  def SIMPLE(s: String): SimpleStringQuery = simple(s)
  implicit def range(s: String): RangeQuery = RangeQuery(s)
  def RANGE(s: String): RangeQuery = range(s)
  implicit def exists(s: String): ExistsQuery = ExistsQuery(s)
  def EXISTS(s: String): ExistsQuery = exists(s)

  implicit def stringToDirectory(s: String): Directory = Directory(s)

  implicit def extendProducts(p: Product): ProductExtender = new ProductExtender(p)
  implicit def extendStrings(s: String): StringExtender = new StringExtender(s)
  implicit def extendDirectories(d: Directory): DirectoryExtender = new DirectoryExtender(d)
  implicit def extendTerms(t: Term): TermExtender = new TermExtender(t)

}

package elsa {

  private[elsa] class ProductExtender(p: Product) {
    def -->(values: String*): Term = Term(p.productIterator.mkString(","), values: _*)
  }

  private[elsa] class StringExtender(s: String) {
    def -->(values: String*): Term = Term(s, values: _*)

    def /(dir: String): Directory = Directory(dir, Some(Directory(s)))

    def matchQuery(values: String*): MatchQuery = MatchQuery(s, values.mkString(" "))

    def MATCH(values: String*): MatchQuery = matchQuery(values: _*)

    def phrase(values: String*): MatchPhraseQuery = MatchPhraseQuery(s, values.mkString(" "))

    def PHRASE(values: String*): MatchPhraseQuery = phrase(values: _*)

    def phrasePrefix(values: String*): MatchPhrasePrefixQuery = MatchPhrasePrefixQuery(s, values.mkString(" "))

    def PHRASE_PREFIX(values: String*): MatchPhrasePrefixQuery = phrasePrefix(values: _*)

    def multiMatch(values: String*): MultiMatchQuery = MultiMatchQuery(s, values.mkString(" "))

    def MULTI_MATCH(values: String*): MultiMatchQuery = multiMatch(values: _*)

    def common(values: String*): CommonQuery = CommonQuery(s, values.mkString(" "))

    def COMMON(values: String*): CommonQuery = common(values: _*)

    def string: StringQuery = StringQuery(s)

    def STRING: StringQuery = string

    def simple: SimpleStringQuery = SimpleStringQuery(s)

    def SIMPLE: SimpleStringQuery = simple

    def term(values: String*): TermQuery = TermQuery(s, values.mkString(" "))

    def TERM(values: String*): TermQuery = term(values: _*)

    def range: RangeQuery = RangeQuery(s)

    def RANGE: RangeQuery = range

    def exists: ExistsQuery = ExistsQuery(s)

    def EXISTS: ExistsQuery = exists

    def prefix(values: String*): PrefixQuery = PrefixQuery(s, values.mkString(" "))

    def PREFIX(values: String*): PrefixQuery = prefix(values: _*)

    def wildcard(values: String*): WildcardQuery = WildcardQuery(s, values.mkString(" "))

    def WILDCARD(values: String*): WildcardQuery = wildcard(values: _*)
  }

  private[elsa] class DirectoryExtender(d: Directory) {
    def matchQuery(m: MatchQuery): Query = d.query(m)

    def MATCH(m: MatchQuery): Query = matchQuery(m)

    def phrase(mp: MatchPhraseQuery): Query = d.query(mp)

    def PHRASE(mp: MatchPhraseQuery): Query = phrase(mp)

    def phrasePrefix(mp: MatchPhrasePrefixQuery): Query = d.query(mp)

    def PHRASE_PREFIX(mp: MatchPhrasePrefixQuery): Query = phrasePrefix(mp)

    def multiMatch(mm: MultiMatchQuery): Query = d.query(mm)

    def MULTI_MATCH(mm: MultiMatchQuery): Query = multiMatch(mm)

    def common(c: CommonQuery): Query = d.query(c)

    def COMMON(c: CommonQuery): Query = common(c)

    def string(s: StringQuery): Query = d.query(s)

    def STRING(s: StringQuery): Query = string(s)

    def simple(s: SimpleStringQuery): Query = d.query(s)

    def SIMPLE(s: SimpleStringQuery): Query = simple(s)

    def term(t: TermQuery): Query = d.query(t)

    def TERM(t: TermQuery): Query = term(t)

    def range(r: RangeQuery): Query = d.query(r)

    def RANGE(r: RangeQuery): Query = range(r)

    def exists(e: ExistsQuery): Query = d.query(e)

    def EXISTS(e: ExistsQuery): Query = exists(e)

    def prefix(p: PrefixQuery): Query = d.query(p)

    def PREFIX(p: PrefixQuery): Query = prefix(p)

    def wildcard(w: WildcardQuery): Query = d.query(w)

    def WILDCARD(w: WildcardQuery): Query = wildcard(w)

    def constantScore(c: ConstantScoreQuery): Query = d.query(c)

    def CONSTANT_SCORE(c: ConstantScoreQuery): Query = constantScore(c)

    def bool(b: BoolQuery): Query = d.query(b)

    def BOOL(b: BoolQuery): Query = bool(b)
  }

  private[elsa] class TermExtender(t: Term) {
    def matchQuery(directory: Directory): Query = Query(directory, MatchQuery(t))

    def MATCH(directory: Directory): Query = matchQuery(directory)

    def phrase(directory: Directory): Query = Query(directory, MatchPhraseQuery(t))

    def PHRASE(directory: Directory): Query = phrase(directory)

    def phrasePrefix(directory: Directory): Query = Query(directory, MatchPhrasePrefixQuery(t))

    def PHRASE_PREFIX(directory: Directory): Query = phrasePrefix(directory)

    def multiMatch(directory: Directory): Query = Query(directory, MultiMatchQuery(t))

    def MULTI_MATCH(directory: Directory): Query = multiMatch(directory)

    def common(directory: Directory): Query = Query(directory, CommonQuery(t))

    def COMMON(directory: Directory): Query = common(directory)

    def string(directory: Directory): Query = Query(directory, StringQuery(t))

    def STRING(directory: Directory): Query = string(directory)

    def simple(directory: Directory): Query = Query(directory, SimpleStringQuery(t))

    def SIMPLE(directory: Directory): Query = simple(directory)

    def term(directory: Directory): Query = Query(directory, TermQuery(t))

    def TERM(directory: Directory): Query = term(directory)

    def range(directory: Directory): Query = Query(directory, RangeQuery(t))

    def RANGE(directory: Directory): Query = range(directory)

    def exists(directory: Directory): Query = Query(directory, ExistsQuery(t))

    def EXISTS(directory: Directory): Query = exists(directory)

    def prefix(directory: Directory): Query = Query(directory, PrefixQuery(t))

    def PREFIX(directory: Directory): Query = prefix(directory)

    def wildcard(directory: Directory): Query = Query(directory, PrefixQuery(t))

    def WILDCARD(directory: Directory): Query = wildcard(directory)
  }

}