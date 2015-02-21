package utils

import com.github.tminglei.slickpg._
import slick.driver.PostgresDriver

trait WithPGDriver {
  val driver: PGDriver
}

trait PGDriver extends PostgresDriver
  with PgArraySupport
  with PgDateSupportJoda {

  override lazy val Implicit = new ImplicitsPlus {}
  override val simple = new SimpleQLPlus {}

  trait ImplicitsPlus extends Implicits
    with ArrayImplicits
    with DateTimeImplicits

  trait SimpleQLPlus extends SimpleQL
    with ImplicitsPlus
}

object PGDriver extends PGDriver
