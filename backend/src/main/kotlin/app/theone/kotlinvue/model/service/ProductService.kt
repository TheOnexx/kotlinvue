package app.theone.kotlinvue.model.service

import app.theone.kotlinvue.model.data.jpa.Comment
import app.theone.kotlinvue.model.data.jpa.Product
import app.theone.kotlinvue.model.data.json.CommentJson
import app.theone.kotlinvue.model.data.json.ProductJson
import app.theone.kotlinvue.model.exception.ProductNotFoundException
import app.theone.kotlinvue.model.repository.CategoryRepository
import app.theone.kotlinvue.model.repository.ProductRepository
import app.theone.kotlinvue.model.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class ProductService(
        @Autowired private val productRepository: ProductRepository,
        @Autowired private val userRepository: UserRepository,
        @Autowired private val categoryRepository: CategoryRepository) {

    fun addOrUpdateProduct(productJson: ProductJson) {
        val foundProduct = productJson.id?.let {
            productRepository.findById(it)
        }

        if(foundProduct != null) {
            val product = foundProduct.orElseThrow {
                throw ProductNotFoundException("There is no such product with id=" + productJson.id)
            }
            updateProduct(productJson, product)
        } else {
            createNewProduct(productJson)
        }
    }

    fun addComment(comment: CommentJson) {
        val product = productRepository.findById(comment.productId!!).orElseThrow {
            throw RuntimeException("Product is not found in JSON api")
        }
        val user = userRepository.findById(comment.userId!!).orElseThrow {
            throw RuntimeException("User is not found in JSON api")
        }

        val commentEntity = Comment(
                0,
                user,
                comment.content!!,
                product
        )

        product.addComment(commentEntity)
        user.addComment(commentEntity)

        productRepository.save(product)
    }

    fun allProducts() : Iterable<Product> = productRepository.findAll()


    private fun createNewProduct(productJson: ProductJson) {
        val category = categoryRepository.findById(productJson.category!!).get()

        val newProduct = Product(
                0,
                productJson.name!!,
                productJson.description!!,
                category
        )

        productRepository.save(newProduct)
    }

    private fun updateProduct(productJson: ProductJson, existingProduct: Product) {
        val category = categoryRepository.findById(productJson.category!!).get()
        val updatedProduct = existingProduct.copy(
                name = productJson.name!!,
                description = productJson.description!!,
                category = category
        )
        productRepository.save(updatedProduct)
    }

}