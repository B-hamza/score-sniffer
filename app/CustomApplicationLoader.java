import play.ApplicationLoader;
import play.BuiltInComponentsFromContext;
import play.LoggerConfigurator;
import play.filters.components.HttpFiltersComponents;
import play.components.ConfigurationComponents;
import play.routing.Router;
import play.controllers.AssetsComponents;
import play.libs.ws.ahc.AhcWSComponents;
import play.libs.ws.WSClient;
import com.typesafe.config.Config;

import controllers.Application;
import oauth.Oauth;
import score.Score;
import utils.Conf;
import controllers.Assets;


public class CustomApplicationLoader implements ApplicationLoader {
  @Override
  public play.Application load(ApplicationLoader.Context context) {
    LoggerConfigurator.apply(context.environment().classLoader())
        .ifPresent(loggerConfigurator ->
            loggerConfigurator.configure(
                context.environment(),
                context.initialConfig()
            )
        );

    return new CustomComponent(context).application();
  }
}


class CustomComponent extends BuiltInComponentsFromContext 
implements HttpFiltersComponents, AssetsComponents, ConfigurationComponents, AhcWSComponents {

  public CustomComponent(ApplicationLoader.Context context) {
    super(context);
  }

  @Override
  public Router router() {
    Application applicationController = new Application();
    WSClient wsClient = wsClient();
    Conf conf = Conf.load(config());
    Oauth auth = new Oauth(conf, wsClient);
    Score score = new Score(conf, wsClient);
    Assets assets = new Assets(scalaHttpErrorHandler(), assetsMetadata());
    return new router.Routes(scalaHttpErrorHandler(), applicationController, auth, score, assets).asJava();
  }
}
