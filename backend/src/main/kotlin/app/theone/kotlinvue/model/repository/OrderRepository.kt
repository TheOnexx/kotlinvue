package app.theone.kotlinvue.model.repository

import app.theone.kotlinvue.model.data.jpa.Order
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Int> {
}