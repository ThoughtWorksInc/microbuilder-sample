package proxy;

using com.qifun.jsonStream.Plugins;

@:nativeGen
@:build(com.qifun.jsonStream.JsonDeserializer.generateDeserializer([
    "com.thoughtworks.restRpc.core.Failure",
    "model.User"
]))
class RestRpcDeserializer {
}
