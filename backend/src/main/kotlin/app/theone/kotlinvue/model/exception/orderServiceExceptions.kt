package app.theone.kotlinvue.model.exception

import java.lang.RuntimeException

class OrderAlreadyExistsException(orderId: Int) : RestApiException("Order $orderId already exists")

class OrderNotFoundException(orderId: Int) : RestApiException("Order $orderId not found")