import org.specs2.Specification

object RpcSampleSpec extends Specification {
  def is = s2"""

   This is a specification for the RpcSample

   The RpcSample should
     have identity startWith                            $e1
     have identity endWith                             $e2
                                                       """

  def e1 = RpcSample.identity must startWith("the rest-rpc-sample depends on")

  def e2 = RpcSample.identity must endWith(RpcPlay.identity)

}
