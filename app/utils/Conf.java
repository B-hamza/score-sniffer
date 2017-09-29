package utils;

import com.typesafe.config.Config;

public class Conf {

  private final String host;
  private final String graphHost;
  private final String graphVersion;
  private final String authUri;
  private final String clientId;
  private final String secretApp;
  private final String authTokenUri;

  private Conf(String host, String graphHost, String graphVersion, String authUri, String clientId, String secretApp, String authTokenUri) {
    this.host = host;
    this.graphHost = graphHost;
    this.graphVersion = graphVersion;
    this.authUri = authUri;
    this.clientId = clientId;
    this.secretApp = secretApp;
    this.authTokenUri = authTokenUri;
  }

  static public Conf load(Config conf) {
    return new Conf(
      conf.getString("oauth.facebook.host"),
      conf.getString("oauth.facebook.graphHost"),
      conf.getString("oauth.facebook.graphVersion"),
      conf.getString("oauth.facebook.authUri"),
      conf.getString("oauth.facebook.apiId"),
      conf.getString("oauth.facebook.secretApp"),
      conf.getString("oauth.facebook.authTokenUri")
    );
  }

  public String getHost() {
    return this.host;
  }

  public String getGraphHost() {
    return this.graphHost;
  }
  
  public String getGraphVersion() {
    return this.graphVersion;
  }

  public String getAuthUri() {
    return this.authUri;
  }

  public String getClientId() {
    return this.clientId;
  }

  public String getSecretApp() {
    return this.secretApp;
  }

  public String getAuthTokenUri() {
    return this.authTokenUri;
  }


}
