package controllers

import java.util.concurrent.TimeoutException

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration

import org.joda.time.LocalDateTime

import models.Company
import models.current.dao
import models.current.dao._
import models.current.dao.driver.simple._
import play.api.Logger
import play.api.Play.current
import play.api.data.Form
import play.api.data.Forms.ignored
import play.api.data.Forms.mapping
import play.api.data.Forms.nonEmptyText
import play.api.db.slick.DBAction
import play.api.db.slick.dbSessionRequestAsSession
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.concurrent.Promise
import play.api.mvc.Action
import play.api.mvc.Controller
import views.html

object Application extends Controller with Application

trait Application extends Controller {
  this: Controller =>

  val log: Logger = Logger(this.getClass)

  /**
   * Describe the Company form (used in both edit and create screens).
   */
  val companyForm = Form(
    mapping(
      "id" -> ignored(Some(0): Option[Long]),
      "name" -> nonEmptyText,
      "created" -> ignored(new LocalDateTime),
      "updated" -> ignored(new LocalDateTime))(Company.apply)(Company.unapply))

  /**
   * Handle default path requests, redirect to Company list
   */
  def index = Action { Home }

  /**
   * This result directly redirect to the application home.
   */
  val Home = Redirect(routes.Application.list())

  /**
   * Display the paginated list of companies.
   *
   * @param page Current page number (starts from 0)
   * @param orderBy Column to be sorted
   * @param filter Filter applied on company names
   */
  def list(page: Int, orderBy: Int, filter: String) = DBAction { implicit request =>
    val pageData = dao.list(page = page, orderBy = orderBy, filter = ("%" + filter + "%"))
    Ok(html.list(pageData, orderBy, filter))
  }

  /**
   * Display the 'edit form' of a existing company.
   *
   * @param id Id of the Company to edit
   */
  def edit(id: Long) = DBAction { implicit request =>
    companies.filter(_.id === id).firstOption match {
      case Some(company) => Ok(html.editForm(id, companyForm.fill(company)))
      case None          => NotFound
    }
  }

  /**
   * Handle the 'edit form' submission
   *
   * @param id Id of the Company to edit
   */
  def update(id: Long) = DBAction { implicit request =>
    companyForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.editForm(id, formWithErrors)),
      company => {
        val companyToUpdate: Company = company.copy(Some(id))
        companies.filter(_.id === id).update(companyToUpdate)
        Home.flashing("success" -> s"Company ${company.name} has been updated")
      })
  }

  /**
   * Display the 'new Company form'.
   */
  def create = Action {
    Ok(html.createForm(companyForm))
  }

  /**
   * Handle the 'new Company form' submission.
   */
  def save = DBAction { implicit request =>
    companyForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.createForm(formWithErrors)),
      company => {
        companies.insert(company)
        val msg = s"Company ${company.name} has been created"
        Home.flashing("success" -> msg)
      })
  }

  /**
   * Handle Company deletion.
   */
  def delete(id: Long) = DBAction { implicit request =>
    companies.filter(_.id === id).delete
    Home.flashing("success" -> "Company has been deleted")
  }

}