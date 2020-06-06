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

    fun addOrUpdateProduct(productJson: ProductJson): Product {
        val foundProduct = productJson.id?.let {
            productRepository.findById(it)
        }

        return if(foundProduct != null) {
            val product = foundProduct.orElseThrow {
                throw ProductNotFoundException("There is no such product with id=" + productJson.id)
            }
            updateProduct(productJson, product)
        } else {
            createNewProduct(productJson)
        }
    }

    fun addComment(comment: CommentJson): Comment {
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

        val savedProduct = productRepository.save(product)
        return savedProduct.comments.last()

    }

    fun allProducts() : Iterable<Product> = productRepository.findAll()

    fun editComment(comment: CommentJson) : Comment {
        val optionalRelatedProduct = productRepository.findById(comment.productId!!)
        val relatedProduct = optionalRelatedProduct.orElseThrow {
            throw ProductNotFoundException("There is no such product with id ${comment.productId}")
        }

        val foundComment = relatedProduct.comments.find {
            it.commentId == comment.commentId
        }
        foundComment?.content = comment.content!!

        val savedProduct = productRepository.save(relatedProduct)

        return savedProduct.comments.find { it.commentId == comment.commentId }!!
    }

    fun removeComment(comment: CommentJson) : Boolean {
        val optionalRelatedProduct = productRepository.findById(comment.productId!!)
        val relatedProduct = optionalRelatedProduct.orElseThrow {
            throw ProductNotFoundException("There is no such product with id ${comment.productId}")
        }
        val isRemoved = relatedProduct.comments.removeIf {
            it.commentId == comment.commentId
        }
        return if (isRemoved) {
            productRepository.save(relatedProduct)
            true
        } else {
            false
        }
    }


    private fun createNewProduct(productJson: ProductJson): Product {
        val category = categoryRepository.findById(productJson.category!!).get()

        val newProduct = Product(
                0,
                productJson.name!!,
                productJson.description!!,
                category
        )

        return productRepository.save(newProduct)
    }

    private fun updateProduct(productJson: ProductJson, existingProduct: Product): Product {
        val category = categoryRepository.findById(productJson.category!!).get()

        existingProduct.update(productJson, category)
        return productRepository.save(existingProduct)
    }

}