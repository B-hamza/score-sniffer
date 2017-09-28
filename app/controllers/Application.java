package controllers;

import play.*;
import play.mvc.*;

public class Application extends Controller {
  
  public Result index() {
    return ok("It works!");
  }
  
  
}
