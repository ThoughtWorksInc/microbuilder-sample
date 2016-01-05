package rpc;

import jsonStream.rpc.Future;
import model.User;

@:structuralFailure(model.GithubFailure)
interface IUserRpc {

  @:responseContentType("application/json; charset=utf-8")
  @:route("GET", "/users/{username}")
  function getSingleUser(username:String):Future<User>;
  
}
