import org.joda.time.LocalDateTime

import models.Company
import models.current.dao._
import models.current.dao.driver.simple._
import play.api.Application
import play.api.GlobalSettings
import play.api.Play.current
import play.api.db.slick.DB

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    InitialData.insert()
  }
}

/** Initial set of data to be imported into the sample application. */
object InitialData {
  def insert(): Unit = {
    DB.withSession { implicit s: Session =>
      if (companies.list.size == 0) {
        Seq(
          Company(Option(1L), "Apple Inc.", new LocalDateTime, new LocalDateTime),
          Company(Option(2L), "Thinking Machines", new LocalDateTime, new LocalDateTime),
          Company(Option(3L), "RCA", new LocalDateTime, new LocalDateTime),
          Company(Option(4L), "Netronics", new LocalDateTime, new LocalDateTime),
          Company(Option(5L), "Tandy Corporation", new LocalDateTime, new LocalDateTime),
          Company(Option(6L), "Commodore International", new LocalDateTime, new LocalDateTime),
          Company(Option(7L), "MOS Technology", new LocalDateTime, new LocalDateTime),
          Company(Option(8L), "Micro Instrumentation and Telemetry Systems", new LocalDateTime, new LocalDateTime),
          Company(Option(9L), "IMS Associates, Inc.", new LocalDateTime, new LocalDateTime),
          Company(Option(10L), "Digital Equipment Corporation", new LocalDateTime, new LocalDateTime)).foreach(company => companies.insert(company))
      }
    }
  }
}