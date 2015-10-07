package rpc;

import com.qifun.jsonStream.rpc.Future;
import model.User;

interface IUserRpc {
    @:route("GET", "/users/{username}")
    function getSingleUser(username:String):Future<User>;
}