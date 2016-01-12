package githubApiSample

import com.ning.http.client.AsyncHttpClientConfig
import com.thoughtworks.microbuilder.play.{RpcController, RpcEntry, PlayOutgoingJsonService}
import play.api.libs.ws.{WSAPI, WSClient}
import com.thoughtworks.microbuilder.sample._
import com.thoughtworks.microbuilder.sample.proxy.{MicrobuilderIncomingProxyFactory, MicrobuilderOutgoingProxyFactory, MicrobuilderRouteConfigurationFactory}
import router.Routes
import play.api.libs.ws.ning.{NingWSClient, NingWSComponents}
import play.api.routing.Router
import play.api.{BuiltInComponentsFromContext, Application, ApplicationLoader}
import play.api.ApplicationLoader.Context
import rpc.IUserRpc

class SampleLoader extends ApplicationLoader {
  override def load(context: Context): Application = {

    val components = new BuiltInComponentsFromContext(context) with NingWSComponents {
      lazy val routeConfiguration = MicrobuilderRouteConfigurationFactory.routeConfiguration_com_thoughtworks_microbuilder_sample_rpc_IUserRpc;
      lazy val outgoingJsonService = new PlayOutgoingJsonService("https://api.github.com/", routeConfiguration, wsApi)(actorSystem.dispatcher)
      lazy val userRpc = MicrobuilderOutgoingProxyFactory.outgoingProxy_com_thoughtworks_microbuilder_sample_rpc_IUserRpc(outgoingJsonService)
      lazy val sampleController = new SampleController(userRpc)(actorSystem.dispatcher)
      lazy val assets = new controllers.Assets(httpErrorHandler)
      lazy val rpcEntries = Seq(
        new RpcEntry(routeConfiguration,
          MicrobuilderIncomingProxyFactory.incomingProxy_com_thoughtworks_microbuilder_sample_rpc_IUserRpc(new UserRpc()))
      )
      lazy val rpcController = new RpcController(rpcEntries)
      override lazy val router: Router = new Routes(httpErrorHandler, sampleController, assets, rpcController)
    }

    components.application

  }
}
