package app.theone.kotlinvue.model.repository

import app.theone.kotlinvue.model.data.jpa.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, Int>{}