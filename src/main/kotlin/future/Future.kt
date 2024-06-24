package main.kotlin.future

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun sum(a: Int, b: Int) = a + b

fun main() {
    val pool = Executors.newSingleThreadExecutor()
    val future = pool.submit(Callable {
        sum(100, 200)
    })

    println("계산 시작")
    // 작업이 마칠때까지 기다린다.
//    val futureResult = future.get()
//    println(futureResult)

    // get()에 인자값으로 타임아웃 값을 설정할 수 있다.
    // 타임아웃 시간을 지정하지 않으면 작업이 마칠 떄까지 계속 대기한다.
    val futureResult2 = future.get(1000, TimeUnit.MILLISECONDS)
    println(futureResult2)
    println("계산 종료")
}
