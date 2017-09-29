package score;

import play.*;
import play.mvc.*;
import play.libs.ws.WSClient;

import utils.Conf;

public class Score extends Controller {

  private final Conf conf;
  private final WSClient ws;

  public Score(Conf conf, WSClient ws) {
    this.conf = conf;
    this.ws = ws;
  }

  public Result scoreFromLikes(String accessToken) {
    

    return ok(accessToken);
  }

}
