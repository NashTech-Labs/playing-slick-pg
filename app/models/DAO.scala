package models

import play.api.db.slick.DB
import utils.PGDriver

class DAO(override val driver: PGDriver) extends CompanyComponent {
  import driver.simple._

  val companies = TableQuery(new Companies(_))

  /**
   * Count company with a filter
   *
   * @param filter
   */
  def count(filter: String)(implicit s: Session): Int =
    Query(companies.filter(_.name.toLowerCase like filter.toLowerCase).length).first

  /**
   * Return a page of companies
   * @param page
   * @param pageSize
   * @param orderBy
   * @param filter
   */
  def list(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%")(implicit s: Session): Page[Company] = {
    val offset = pageSize * page
    val query =
      (for {
        company <- companies
        if company.name.toLowerCase like filter.toLowerCase()
      } yield (company))
    val sort = orderBy match {
      case -4 => query.sortBy(_.updated.desc)
      case -3 => query.sortBy(_.created.desc)
      case -2 => query.sortBy(_.name.desc)
      case 2 => query.sortBy(_.name)
      case 3 => query.sortBy(_.created)
      case 4 => query.sortBy(_.updated)
      case _ => query.sortBy(_.name)
    }
    val totalRows = count(filter)
    val result = sort.drop(offset).take(pageSize).list
    Page(result, page, offset, totalRows)
  }

}

object current {
  val dao = new DAO(DB(play.api.Play.current).driver.asInstanceOf[PGDriver])
}

