package app.theone.kotlinvue.model.repository

import app.theone.kotlinvue.model.data.jpa.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : CrudRepository<Category, Int> {}