package proxy;

using jsonStream.Plugins;
using proxy.MicrobuilderDeserializer;
using proxy.MicrobuilderSerializer;


@:nativeGen
@:build(jsonStream.rpc.OutgoingProxyFactory.generateOutgoingProxyFactory([
  "rpc.IUserRpc"
]))
class MicrobuilderOutgoingProxyFactory {}
