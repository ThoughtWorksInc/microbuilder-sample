package proxy;

using com.qifun.jsonStream.Plugins;

@:nativeGen
@:build(com.qifun.jsonStream.JsonSerializer.generateSerializer([
    "com.thoughtworks.restRpc.core.Failure",
    "model.User"
]))
class RestRpcSerializer {
}