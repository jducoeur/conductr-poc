package clustertest

import akka.actor._
import akka.cluster.Cluster

/**
 * @author jducoeur
 */
class ShardedActor extends Actor {
  import ShardedActor._
  
  var nInvocations = 0
  
  def receive = {
    case Invoke(name) => {
      nInvocations += 1
      sender ! ShardedResponse(nInvocations, clusterAddress)
    }
  }
  
  def clusterAddress:String = {
    val cluster = Cluster.get(context.system)
    cluster.selfUniqueAddress.toString
  }
}

object ShardedActor {
  case class Invoke(name:String)
  case class ShardedResponse(n:Int, address:String)
  
  def props = Props(classOf[ShardedActor])
}
