package githubApiSample

import com.thoughtworks.microbuilder.play.Implicits._
import com.thoughtworks.microbuilder.sample.model.User
import com.thoughtworks.microbuilder.sample.rpc.IUserRpc
import jsonStream.rpc.IFuture1

import scala.concurrent.Future

/**
  * @author 杨博 (Yang Bo) &lt;pop.atry@gmail.com&gt;
  */
class UserRpc extends IUserRpc {

  override def getSingleUser(username: String): IFuture1[User] = {
    val user = new User()
    user.name = "xiaoshao"
    user.email ="zwshao@thoughtworks.com"
    user.followers = 100
    user.avatar_url = "http://s1.rui.au.reastatic.net/rui-static/img/rea-logo-v3.png"
    Future.successful(user)
  }

}
