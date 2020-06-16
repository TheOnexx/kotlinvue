package app.theone.kotlinvue.model.service

import app.theone.kotlinvue.model.data.jpa.Order
import app.theone.kotlinvue.model.data.json.OrderJson

interface OrderService {
    fun addOrder(orderJson: OrderJson): Order
    fun cancelOrder(orderId: Int)
    fun findAllOrdersByUser(userId: Int): Iterable<Order>
    fun changeProductsInOrder(orderJson: OrderJson): Order

}