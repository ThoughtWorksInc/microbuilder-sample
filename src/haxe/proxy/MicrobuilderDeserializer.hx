package proxy;

using com.qifun.jsonStream.Plugins;

@:nativeGen
@:build(com.qifun.jsonStream.JsonDeserializer.generateDeserializer([
    "com.thoughtworks.microbuilder.core.Failure",
    "model.User"
]))
class MicrobuilderDeserializer {
}
