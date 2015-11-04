[![Build Status](https://travis-ci.org/ThoughtWorksInc/microbuilder-sample.svg)](https://travis-ci.org/ThoughtWorksInc/microbuilder-sample)
[![Codacy Badge](https://www.codacy.com/project/badge/daa4b0e6de4647fc94eeb01fbe331438)](https://www.codacy.com/app/zhanglongyang/microbuilder-sample)

##What is Microbuilder
*Microbuilder*​ is a toolset that helps you build systems across multiple micro-services and multiple languages.

It can help you to quickly generate http service client code with quite few haxe definition code.

##Example Problem

Say I want to call github REST API: `https://api.github.com/users/Atry`

The response is like the following:
```
{
login: "Atry",
id: 601530,
...
name: "杨博 (Yang Bo)",
company: "ThoughtWorks",
...
}
```

Isn't it great if you can have a free, generated method as following:

`function getSingleUser(username:String):Future<User>;`

And The method can retrive the json response and automatically deserialize it to a `User` model?

It's easy to do it using MicroBuilder.

##Use MicroBuilder in Play
As you see in this repo, MicroBuilder provides a Play adaptor. To use this adaptor, your folder stucture will look like this:

```
.
├── app
│   └── githubApiSample
│       ├── SampleController.scala
│       ├── SampleLoader.scala
│       └── userPage.scala.html
├── conf
│   ├── application.conf
│   └── routes
├── build.sbt
├── src
│   └── haxe
│       ├── model
│       │   └── User.hx
│       ├── proxy
│       │   ├── MicrobuilderDeserializer.hx
│       │   ├── MicrobuilderOutgoingProxyFactory.hx
│       │   ├── MicrobuilderRouteConfigurationFactory.hx
│       │   └── MicrobuilderSerializer.hx
│       └── rpc
│           └── IUserRpc.hx
```

Besides the app folder, conf folder as usual, you should also have one src folder. You should have model, proxy, rpc folders as shown. 

Put your model definition in model package, and multiple files are allowed.

Put your rpc interface definition in rpc package, and multiple definitions are allowed.

The files in proxy folder will be similar with what in the sample. The only difference would be the models name and rpc interfaces name in respective annotations.

You can simply copy the build.sbt to your own project.

After compilation, java code will be generated from haxe code. Then you can use them in your Play project as in this sample project.

##Some Details

###RPC interface definition
As you can see in the following definition:
```
package rpc;

import jsonStream.rpc.Future;
import model.User;

interface IUserRpc {
    @:route("GET", "/users/{username}")
    function getSingleUser(username:String):Future<User>;
}
```
As you see, you can specify the url template in @route template. So if you call `getSingleUser("Atry")`, you are actually making a http request to `https://api.github.com/users/Atry`. 

Since making http request is a time consuming operation, we let it return a `Future` type for you to consume.

The url template syntax complys with [rfc6570](https://tools.ietf.org/html/rfc6570)

###The return type

Be aware, this return type `Future` is not a scala standard `Future`, that's why we provide a implicit conversion to convert this `jsonStream.rpc.Future` to `scala.concurrent`. To utilise this implicit conversion, add the following line to the file where you want to consume the Future result:

`import com.thoughtworks.microbuilder.play.Implicits._`

###Error response handling
If your server response is in a fixed format. For example, like this:
```
{
    "code":1,
    "errorMsgs": [
        "error message 1",
        "error message 2",
        "error message 3"
    ]
}
```

Then you can configure an annotation on rpc interface definition as follow:

```
class XXXFailure {
    public var code:Int;
    public var errorMsg:String;
}
```

```
@:structuralFailure(XXXFailure)
interface MyRpcWithStructuralException {
...
}
```

Then the error response will be automatically deserialized, and be available in `Future`'s onFailure handler.
Check the `Exception handing` section for how to use this deserialized object.

If error response is not structured, then no bother to add that annotation.

###Exception handling

There are four type of exceptions that may yield from `Future`'s onFailure handler:

* StructuralApplicationException\[A\](data: A, code: Int)

As mentioned in `Error response handling` section, if your server error response is structured and unified, then you can configure the `structuralFailure` annotation and expect this failure.
`data` is the deserialized object representing the error message, and `code` is the response status.


* TextApplicationException(reason: String, code: Int)

When your server error response is not structured, and you didn't configure the `structuralFailure` annotation, then you can expect this failure.
`reason` is exactly the same as what you get from the server, and `code` is the response status.
  
  
* NativeException(reason: String)

You will meet this exception for IO errors.
`reason` is the IO exception message.

* WrongResponseFormatException(reason: String)

You will meet this exception if the server response is not a json.
`reason` will show you the wrong json.
