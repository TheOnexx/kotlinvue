package app.theone.kotlinvue.model.exception

import java.lang.RuntimeException

class OrderAlreadyExistsException(orderId: Int) : RuntimeException("Order $orderId already exists")