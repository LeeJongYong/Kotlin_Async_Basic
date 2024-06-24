package completablefuture

import main.kotlin.future.sum
import java.util.concurrent.CompletableFuture

fun main() {
    val completableFuture = CompletableFuture.supplyAsync {
        Thread.sleep(2000)
        sum(100, 200)
    }

    println("계산 시작")
    completableFuture.thenApplyAsync(::println) // 논블로킹으로 동작

//    val result = completableFuture.get() // 블로킹으로 동작 - 결과값이 이미 나왔으므로 아래 로직은 실행 X
//    println(result)

    // isDone : thread의 완료여부를 boolean 값으로 리턴
    // isCanceled : thread의 취소상태를 boolean 값으로 리턴
    // isCompletedExceptionally : thread 동작 중 에러 발생여부를 boolean 값으로 리턴
    while (!completableFuture.isDone) {
        Thread.sleep(500)
        println("계산 결과를 집계 중입니다.")
    }
    println("계산 종료")

    /*
    * completeableFuture - future의 상위호환
    * future와 다르게 작업이 종료될 떄까지 기다리는 블로킹 방식이 아닌
    * 작업 종료와 상관없이 이후 작업을 진행히시키는 논블로킹 방식을 제공하며
    * 블로킹 방식을 사용해야할 경우 future와 동일하게 get()을 사용하면 된다.
    * */
}
