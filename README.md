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
  
MicroBuilder provides a Play adaptor. To use this adaptor, your folder stucture will look like this:

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
│       │   ├── RestRpcDeserializer.hx
│       │   ├── RestRpcOutgoingProxyFactory.hx
│       │   ├── RestRpcRouteConfigurationFactory.hx
│       │   └── RestRpcSerializer.hx
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

import com.qifun.jsonStream.rpc.Future;
import model.User;

interface IUserRpc {
    @:route("GET", "/users/{username}")
    function getSingleUser(username:String):Future<User>;
}
```
As you see, you can specify the url template in @route template. So if you call `getSingleUser("Atry")`, you are actually making a http request to `https://api.github.com/users/Atry`. And the response is a JSON like the following:

Since making http request is a time consuming operation, we let it return a `Future` type for you to consume.

The url template syntax complys with [rfc6570](https://tools.ietf.org/html/rfc6570)

###The return type

Be aware, this return type `Future` is not a scala standard `Future`, that's why we provide a implicit conversion to convert this `com.qifun.jsonStream.rpc.Future` to `scala.concurrent`. To utilise this implicit conversion, add the following line to the file where you want to consume the Future result:

`import com.thoughtworks.restRpc.play.Implicits._`

