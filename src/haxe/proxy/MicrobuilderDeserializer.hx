package proxy;

using jsonStream.Plugins;

@:nativeGen
@:build(jsonStream.JsonDeserializer.generateDeserializer([
    "com.thoughtworks.microbuilder.core.Failure",
    "model.User"
]))
class MicrobuilderDeserializer {
}
