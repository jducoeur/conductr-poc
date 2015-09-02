package controllers

import scala.concurrent.ExecutionContext.Implicits.global

import play.api._
import play.api.mvc._

import clustertest._

object Application extends Controller {

  def index(name:String) = Action.async {
    ActorManager.invoke(name) map { result =>
      Ok(views.html.index(s"$name lives on ${result.address}; this was #${result.n}"))      
    }
  }

}
