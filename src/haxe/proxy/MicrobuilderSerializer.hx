package proxy;

using com.qifun.jsonStream.Plugins;

@:nativeGen
@:build(com.qifun.jsonStream.JsonSerializer.generateSerializer([
    "com.thoughtworks.microbuilder.core.Failure",
    "model.User"
]))
class MicrobuilderSerializer {
}
