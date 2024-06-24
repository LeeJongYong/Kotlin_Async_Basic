package main.kotlin.observerpattern

import java.util.*

class Coffee(val name: String)

// Subject
class Barista : Observable() {

    private lateinit var coffeeName: String

    fun orderCoffee(name: String) {
        this.coffeeName = name
    }

    fun makeCoffee() {
        setChanged()
        notifyObservers(Coffee(this.coffeeName))
    }

}

// Observer
class Customer(val name: String) : Observer {

    override fun update(o: Observable?, arg: Any?) {
        val coffee = arg as Coffee
        println("${name}이 ${coffee.name}을 받았습니다")
    }
}


fun main() {
    val barista = Barista()
    barista.orderCoffee("아이스 아메리카노")

    val customer = Customer("고객1")
    val customer2 = Customer("고객2")
    val customer3 = Customer("고객3")

    barista.addObserver(customer)
    barista.addObserver(customer2)
    barista.addObserver(customer3)

    barista.makeCoffee()

    /*
    * 옵저버 패턴은
    * 관찰의 대상이 변화가 있을 경우 자동으로 관찰자인 옵저버에게 알림으로서 실시간으로 변화를 감지할 수 있다.
    * */
}