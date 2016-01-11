package com.thoughtworks.microbuilder.sample.rpc;

import jsonStream.rpc.Future;
import com.thoughtworks.microbuilder.sample.model.User;

@:structuralFailure(com.thoughtworks.microbuilder.sample.model.GithubFailure)
interface IUserRpc {

  @:responseContentType("application/json; charset=utf-8")
  @:route("GET", "/users/{username}")
  function getSingleUser(username:String):Future<User>;
  
}
