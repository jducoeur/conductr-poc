import play.api._
import play.api.Play.current
import play.api.libs.concurrent.Akka

import akka.actor._

import com.typesafe.config.ConfigFactory
import com.typesafe.conductr.bundlelib
import com.typesafe.conductr.bundlelib.play.StatusService
import com.typesafe.conductr.bundlelib.play.ConnectionContext.Implicits.defaultContext

import clustertest._

/**
 * @author jducoeur
 */
object Global extends GlobalSettings {
  override def onStart(app: Application) {
    val config = bundlelib.akka.Env.asConfig
    val systemName = sys.env.getOrElse("BUNDLE_SYSTEM", "MyApp1")
    val appSystem = ActorSystem(systemName, config.withFallback(ConfigFactory.load()))
    
    ActorManager.setupSharding(appSystem)
    StatusService.signalStartedOrExit()
  }
  
  val totalConfiguration = super.configuration ++ Configuration(bundlelib.play.Env.asConfig)

  override def configuration: Configuration = totalConfiguration
}
