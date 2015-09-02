package clustertest

import scala.concurrent.Future
import scala.concurrent.duration._

import akka.actor._
import akka.pattern._
import akka.contrib.pattern.{ClusterSharding, ShardRegion}
import akka.util.Timeout

/**
 * This is pretty slash-and-burn POC code, so we're not going to do anything smart with
 * the Ecology or anything. Instead, we have one crude static entry point, here, that
 * deals with all the setup and routing.
 * 
 * @author jducoeur
 */
object ActorManager {
  
  import ShardedActor._
  
  var region:ActorRef = null
  
  val idExtractor:ShardRegion.IdExtractor = {
    case msg @ Invoke(name) => (name, msg)
  }
  
  val shardResolver:ShardRegion.ShardResolver = msg => msg match {
    case msg @ Invoke(name) => (name.hashCode() % 30).toString()
  }
  
  def setupSharding(system:ActorSystem) = {
    region = ClusterSharding(system).start(
        typeName = "ShardedActor", 
        entryProps = Some(ShardedActor.props), 
        idExtractor = idExtractor, 
        shardResolver = shardResolver)
  }
  
  val initTermDuration = 30 seconds
  implicit val initTermTimeout = Timeout(initTermDuration)
  
  def invoke(name:String):Future[ShardedResponse] = {
    (region ? Invoke(name)).mapTo[ShardedResponse]
  }
}
