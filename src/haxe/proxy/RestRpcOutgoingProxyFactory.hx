package proxy;

using com.qifun.jsonStream.Plugins;
using proxy.RestRpcDeserializer;
using proxy.RestRpcSerializer;


@:nativeGen
@:build(com.qifun.jsonStream.rpc.OutgoingProxyFactory.generateOutgoingProxyFactory([
  "rpc.IUserRpc"
]))
class RestRpcOutgoingProxyFactory {}