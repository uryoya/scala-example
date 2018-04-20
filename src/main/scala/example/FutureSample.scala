// Dwango Scala資料
// Future/Promiseについて (https://dwango.github.io/scala_text/future-and-promise.html)
// Futureとは

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object FutureSample extends App {
  val s = "Hello"
  val f: Future[String] = Future {
    Thread.sleep(1000)
    s + "future!"
  }

  f.foreach{ case s: String =>
    println(s)
  }

  println(f.isCompleted) // false

  Thread.sleep(5000) // Hello future!

  println(f.isCompleted) // true

  val f2: Future[String] = Future {
    Thread.sleep(1000)
    throw new RuntimeException("わざと失敗")
  }

  f2.failed.foreach { case e: Throwable =>
    println(e.getMessage)
  }

  Thread.sleep(5000) // わざと失敗

  println(f2.isCompleted)
}

