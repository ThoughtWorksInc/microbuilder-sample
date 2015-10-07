package githubApiSample

import com.qifun.jsonStream.rpc.ICompleteHandler1
import com.thoughtworks.restRpc.play.Implicits._
import model.User
import play.api.mvc._
import rpc.IUserRpc

import scala.concurrent.{Promise, ExecutionContext, Future}
import scala.util.{Failure, Success}

class SampleController(userRpc: IUserRpc)(implicit ec: ExecutionContext) extends Controller {
  def getUserPage(username: String): Action[AnyContent] = Action.async {

    val promise = Promise[Result]
    userRpc.getSingleUser(username).onComplete {
      case Success(user) => {
        promise.complete(Success(Ok(html.userPage(user))))
      }
      case Failure(throwable) => {
        promise.complete(Success(ServiceUnavailable(throwable.getMessage)))
      }
    }
    promise.future
  }
}
