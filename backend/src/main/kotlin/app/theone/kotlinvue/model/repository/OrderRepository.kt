package app.theone.kotlinvue.model.repository

import app.theone.kotlinvue.model.data.jpa.Order
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface OrderRepository : CrudRepository<Order, Int> {

    @Query("select order from orders order where user_id = :userId")
    fun findOrdersByUserId(@Param("userId") userId: Int): Optional<Iterable<Order>>
}