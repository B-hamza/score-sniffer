package oauth;

import com.typesafe.config.Config;
import play.*;
import play.mvc.*;

public class Oauth extends Controller {

  private Config conf;

  public Oauth(Config conf) {
    this.conf = conf;
  }

  public Result index() {
    String url = conf.getString("oauth.facebook.apiUrl");
    return ok(url);
  }

}
