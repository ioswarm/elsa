package de.ioswarm.elsa

/**
  * Created by andreas on 30.07.17.
  */
case class Directory(dirname: String, prior: Option[Directory] = None) {
  require(dirname.length > 0)

  private def priorName: String = prior match {
    case Some(p) => p.name
    case None => ""
  }

  private def directoryPath: String = if (dirname.startsWith("/")) dirname else "/"+dirname

  def name: String = {
    val pname = priorName
    if (!pname.endsWith("/")) pname+directoryPath else pname+directoryPath.substring(1)
  }

  def query(qry: QueryType): Query = Query(this, qry)

  private[elsa] def prior(directory: Directory): Directory = copy(prior = Some(directory))

  def /(directory: Directory): Directory = directory.prior(this)

  override def toString: String = name
}
