package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index(name:String) = Action {
    Ok(views.html.index(s"You specified the name $name"))
  }

}
