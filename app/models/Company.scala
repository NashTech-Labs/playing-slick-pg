package models

import utils.WithPGDriver
import org.joda.time.LocalDateTime

/**
 * Helper for pagination.
 */
case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

case class Company(id: Option[Long], name: String, created: LocalDateTime, updated: LocalDateTime)

/**
  * This Company component contains the database representation of COMPANY
  *
  * This pattern is called the cake pattern (I think it is because it tastes good :P),
  *
  * Just follow the steps
  * for each Table you want to have:
  *  1. the play.api.db.slick.Profile "self-type" (see below for an example)
  *  2. the import profile.simple._
  *
  * The reason you want to use the cake pattern here is because
  * we imagine we have multiple different databases for production
  * and tests
  */
trait CompanyComponent extends WithPGDriver {
  import driver.simple._

  class Companies(tag: Tag) extends Table[Company](tag, "COMPANY") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name", O.NotNull)
    def created = column[LocalDateTime]("created", O.NotNull)
    def updated = column[LocalDateTime]("updated", O.NotNull)

    def * = (id.?, name, created, updated) <> (Company.tupled, Company.unapply)
  }
  
}
