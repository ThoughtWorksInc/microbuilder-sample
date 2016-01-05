package githubApiSample

import com.ning.http.client.AsyncHttpClientConfig
import com.thoughtworks.microbuilder.play.PlayOutgoingJsonService
import play.api.libs.ws.{WSAPI, WSClient}
import proxy.{MicrobuilderOutgoingProxyFactory, MicrobuilderRouteConfigurationFactory}
import router.Routes
import play.api.libs.ws.ning.{NingWSClient, NingWSComponents}
import play.api.routing.Router
import play.api.{BuiltInComponentsFromContext, Application, ApplicationLoader}
import play.api.ApplicationLoader.Context
import rpc.IUserRpc

class SampleLoader extends ApplicationLoader {
  override def load(context: Context): Application = {

    val components = new BuiltInComponentsFromContext(context) with NingWSComponents {
      lazy val routeConfiguration = MicrobuilderRouteConfigurationFactory.routeConfiguration_rpc_IUserRpc();
      lazy val outgoingJsonService = new PlayOutgoingJsonService("https://api.github.com", routeConfiguration, wsApi)(actorSystem.dispatcher)
      lazy val userRpc = MicrobuilderOutgoingProxyFactory.outgoingProxy_rpc_IUserRpc(outgoingJsonService)
      lazy val sampleController = new SampleController(userRpc)(actorSystem.dispatcher)
      lazy val assets = new controllers.Assets(httpErrorHandler)
      override lazy val router: Router = new Routes(httpErrorHandler, sampleController, assets)
    }

    components.application

  }
}
