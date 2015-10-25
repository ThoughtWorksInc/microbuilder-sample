package proxy;

using jsonStream.Plugins;

@:nativeGen
@:build(jsonStream.JsonSerializer.generateSerializer([
    "com.thoughtworks.microbuilder.core.Failure",
    "model.User"
]))
class MicrobuilderSerializer {
}
