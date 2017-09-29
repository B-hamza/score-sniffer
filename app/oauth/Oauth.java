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
    String host = conf.getString("oauth.facebook.apiUrl");
    String path = conf.getString("oauth.facebook.authUri");
    String clientId = conf.getString("oauth.facebook.apiId");
    String callbackUri = "http://localhost:9000/callback";

    String uri = host+path+"?client_id="+clientId+"&redirect_uri="+callbackUri;

    System.out.println(uri);
  
    return redirect(uri);
  }

  public Result callback(String code) {
    return ok(code);
  }

}
