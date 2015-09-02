import play.api._
import play.api.Play.current
import play.api.libs.concurrent.Akka

import akka.actor._

import clustertest._

/**
 * @author jducoeur
 */
object Global extends GlobalSettings {
  override def onStart(app: Application) {
    ActorManager.setupSharding(Akka.system)
  }
}
