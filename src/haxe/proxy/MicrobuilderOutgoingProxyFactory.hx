package proxy;

using com.qifun.jsonStream.Plugins;
using proxy.MicrobuilderDeserializer;
using proxy.MicrobuilderSerializer;


@:nativeGen
@:build(com.qifun.jsonStream.rpc.OutgoingProxyFactory.generateOutgoingProxyFactory([
  "rpc.IUserRpc"
]))
class MicrobuilderOutgoingProxyFactory {}
