package app.theone.kotlinvue.model.service.impl

import app.theone.kotlinvue.model.data.enums.OrderStatus
import app.theone.kotlinvue.model.data.jpa.Order
import app.theone.kotlinvue.model.data.json.OrderJson
import app.theone.kotlinvue.model.exception.OrderAlreadyExistsException
import app.theone.kotlinvue.model.exception.OrderNotFoundException
import app.theone.kotlinvue.model.exception.UserIdNotFoundException
import app.theone.kotlinvue.model.repository.OrderRepository
import app.theone.kotlinvue.model.repository.ProductRepository
import app.theone.kotlinvue.model.repository.UserRepository
import app.theone.kotlinvue.model.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
        @Autowired val orderRepository: OrderRepository,
        @Autowired val userRepository: UserRepository,
        @Autowired var productRepository: ProductRepository
) : OrderService {
    override fun addOrder(orderJson: OrderJson): Order {
        val orderId = orderJson.orderId
        if(orderId != null) {
            val possibleOrder = orderRepository.findById(orderId)
            if(possibleOrder.isPresent) {
                throw OrderAlreadyExistsException(orderId)
            }
        }
        val possibleUser = userRepository.findById(orderJson.userId!!)
        val user = possibleUser.orElseThrow {
            throw UserIdNotFoundException(orderJson.userId!!)
        }
        val products = productRepository.findAllById(orderJson.orderedProducts!!)

        val newOrder = Order(
                0,
                user,
                OrderStatus.NEW,
                orderJson.total!!,
                products.toMutableList()
        )
        return orderRepository.save(newOrder)
    }

    override fun cancelOrder(orderId: Int) {
        val possibleOrder = orderRepository.findById(orderId)
        val order = possibleOrder.orElseThrow {
            throw OrderNotFoundException(orderId)
        }

        order.status = OrderStatus.CANCELLED

        orderRepository.save(order)

    }

    override fun findAllOrdersByUser(userId: Int): Iterable<Order> {
        val possibleOrders = orderRepository.findOrdersByUserId(userId)
        return possibleOrders.orElseGet {
            emptyList()
        }
    }

    override fun addProductToOrder(orderJson: OrderJson): Order {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeProductFromOrder(orderJson: OrderJson): Order {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}