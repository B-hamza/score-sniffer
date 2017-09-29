package oauth;

import java.util.concurrent.CompletionStage;

import com.typesafe.config.Config;
import play.*;
import play.mvc.*;
import play.libs.ws.*;

import utils.Conf;


/**
 * this is an Oauth used for a rest connection
 * to not use in mobile connection
 * use advanced mobile Auth see : https://developers.facebook.com/docs/facebook-login/ios
 */
public class Oauth extends Controller implements WSBodyReadables {

  private Conf conf;
  private WSClient ws;
  private String callbackUri = "http://localhost:9000/callback";

  public Oauth(Conf conf, WSClient ws) {
    this.conf = conf;
    this.ws = ws;
  }

  public Result index() {
    String uri = conf.getHost()+conf.getAuthUri()+"?client_id="+conf.getClientId()+"&redirect_uri="+callbackUri;

    System.out.println(uri);
  
    return redirect(uri);
  }

  public CompletionStage<Result> callback(String code) {
    String uri = conf.getGraphHost()+conf.getAuthTokenUri()
    +"?client_id="+conf.getClientId()
    +"&redirect_uri="+callbackUri
    +"&client_secret="+conf.getSecretApp()+
    "&code="+code;

    return ws.url(uri).get().thenApply((WSResponse r) -> {
      return ok(r.getBody(json()).findPath("access_token"));
    });
  }

}
