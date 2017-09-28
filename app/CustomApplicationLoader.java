
import play.ApplicationLoader;
import play.BuiltInComponentsFromContext;
import play.LoggerConfigurator;
import play.filters.components.HttpFiltersComponents;
import play.components.ConfigurationComponents;
import play.routing.Router;
import play.controllers.AssetsComponents;
import com.typesafe.config.Config;
import controllers.Application;
import oauth.Oauth;
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
implements HttpFiltersComponents, AssetsComponents, ConfigurationComponents {

  public CustomComponent(ApplicationLoader.Context context) {
    super(context);
  }

  @Override
  public Router router() {
    Application applicationController = new Application();
    Oauth auth = new Oauth(config());
    Assets assets = new Assets(scalaHttpErrorHandler(), assetsMetadata());
    return new router.Routes(scalaHttpErrorHandler(), applicationController, auth, assets).asJava();
  }
}
