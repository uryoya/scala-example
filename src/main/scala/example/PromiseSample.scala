// Dwango Scala資料
// Future/Promiseについて (https://dwango.github.io/scala_text/future-and-promise.html)
// Promiseとは
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Promise, Future}
import scala.concurrent.duration._
import scala.util.{Success, Failure}

object PromiseSample extends App {
  val promiseGetInt: Promise[Int] = Promise[Int]
  val futureByPromise: Future[Int] = promiseGetInt.future // PromsieからFutureを作ることが出来る

  // Promiseが解決された時に実行される処理をFutureを使って書くことができる
  val mappedFuture = futureByPromise.map { i =>
    println(s"Success! i: ${i}")
  }

  // 別スレッドで何か重い処理をして、終わったらPromiseに値を渡す
  Future {
    Thread.sleep(300)
    promiseGetInt.success(1)
  }

  Await.ready(mappedFuture, 5000.millisecond)
}
