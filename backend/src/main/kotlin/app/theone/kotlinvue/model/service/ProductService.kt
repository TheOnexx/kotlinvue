package app.theone.kotlinvue.model.service

import app.theone.kotlinvue.model.data.jpa.Comment
import app.theone.kotlinvue.model.data.jpa.Product
import app.theone.kotlinvue.model.data.json.CommentJson
import app.theone.kotlinvue.model.data.json.ProductJson

interface ProductService {
    fun addOrUpdateProduct(productJson: ProductJson): Product
    fun addComment(comment: CommentJson): Comment
    fun getProductById(id : Int): Product
    fun removeProduct(id: Int)
    fun allProducts() : Iterable<Product>
    fun editComment(comment: CommentJson): Comment
    fun removeComment(comment: CommentJson): Boolean
}