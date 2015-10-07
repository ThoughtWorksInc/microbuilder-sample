package proxy;

@:nativeGen
@:build(com.thoughtworks.restRpc.core.RouteConfigurationFactory.generateRouteConfigurationFactory([
  "rpc.IUserRpc"
]))
class RestRpcRouteConfigurationFactory {}