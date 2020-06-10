package app.theone.kotlinvue.model.service

import app.theone.kotlinvue.model.data.jpa.Category
import app.theone.kotlinvue.model.data.jpa.Product
import app.theone.kotlinvue.model.data.json.CategoryJson

interface CategoryService {
    fun addCategory(categoryJson: CategoryJson): Category
    fun editCategory(categoryJson: CategoryJson): Category
    fun removeCategory(categoryId: Int)

    fun getProductsInCategory(categoryId: Int): Iterable<Product>
}