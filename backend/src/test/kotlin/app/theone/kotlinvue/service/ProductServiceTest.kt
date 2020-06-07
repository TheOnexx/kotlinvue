package app.theone.kotlinvue.service

import app.theone.kotlinvue.JPAConfig
import app.theone.kotlinvue.model.data.jpa.Category
import app.theone.kotlinvue.model.data.jpa.Product
import app.theone.kotlinvue.model.data.jpa.User
import app.theone.kotlinvue.model.data.json.CommentJson
import app.theone.kotlinvue.model.data.json.ProductJson
import app.theone.kotlinvue.model.repository.CategoryRepository
import app.theone.kotlinvue.model.repository.UserRepository
import app.theone.kotlinvue.model.service.ProductService
import app.theone.kotlinvue.model.service.impl.ProductServiceImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.AnnotationConfigContextLoader

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(
        classes = [JPAConfig::class],
        loader = AnnotationConfigContextLoader::class)
class ProductServiceTest : AbstractTransactionalJUnit4SpringContextTests() {
    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {

        val category = Category (
                0,
                "firstTestCategory",
                "description of first category",
                null
        )
        categoryRepository.save(category)

        val user = User(
                0,
                "test1",
                "test@mail",
                "password"
        )

        userRepository.save(user)

    }

    @Test
    fun givenProduct_whenSaved_thenProductAdded() {
        val jsonProduct = productJsonBuild()

        val findAll = categoryRepository.findAll()
        println("categories: $findAll")
        productService.addOrUpdateProduct(jsonProduct)


        val allProducts = productService.allProducts()
        Assert.assertEquals(allProducts.count(), 1)

    }

    @Test
    fun givenProductAndComment_whenAddCommentToProduct_thenCommentAdded() {
        productService.addOrUpdateProduct(productJsonBuild())


        val foundProduct = productService.allProducts().iterator().next()

        val commentJson = commentJsonBuild(foundProduct)
        productService.addComment(commentJson)

        Assert.assertEquals(foundProduct.comments.size, 1)
        println("comments: " + foundProduct.comments)

    }

    @Test
    fun givenTwoProducts_whenSave_thenTwoProductsExist() {
        val product1 = productJsonBuild()
        val product2 = productJsonBuild()

        productService.addOrUpdateProduct(product1)
        productService.addOrUpdateProduct(product2)

        Assert.assertEquals(productService.allProducts().count(), 2)
    }

    @Test
    fun givenProduct_whenUpdate_thenUpdatedProduct() {
        val product = productJsonBuild()

        productService.addOrUpdateProduct(product)

        product.id = productService.allProducts().iterator().next().productId
        product.name = "Changed Name"

        productService.addOrUpdateProduct(product)

        val next = productService.allProducts().iterator().next()

        Assert.assertEquals(product.name, next.name)
    }

    @Test
    fun givenProductWithComment_whenCommentUpdated_thenNewCommentSaved() {
        val product = productJsonBuild()
        val addedProduct = productService.addOrUpdateProduct(product)
        val comment = commentJsonBuild(addedProduct)
        val addedComment = productService.addComment(comment)

        Assert.assertEquals("Comment content", addedComment.content)

        comment.content = "Updated Comment Content"
        comment.commentId = addedComment.commentId
        val editComment = productService.editComment(comment)

        Assert.assertEquals("Updated Comment Content", editComment.content)
    }

    @Test
    fun givenProductWithComment_whenRemoveComment_thenCommentSizeChanged() {
        val product = productJsonBuild()
        val addedProduct = productService.addOrUpdateProduct(product)
        val comment = commentJsonBuild(addedProduct)
        val addedComment = productService.addComment(comment)
        comment.commentId = addedComment.commentId

        Assert.assertEquals(1, productService.allProducts().iterator().next().comments.size)

        productService.removeComment(comment)

        Assert.assertEquals(0, productService.allProducts().iterator().next().comments.size)
    }

    @Test
    fun givenProduct_whenRemoveProduct_theProductRemoved() {
        val product = productJsonBuild()
        val addOrUpdateProduct = productService.addOrUpdateProduct(product)

        Assert.assertEquals(1, productService.allProducts().count())
        println("products ${productService.allProducts()}, productId= ${addOrUpdateProduct.productId}")

        productService.removeProduct(addOrUpdateProduct.productId)

        println("products2 ${productService.allProducts()}, productId= ${addOrUpdateProduct.productId}")
        Assert.assertEquals(0, productService.allProducts().count())



    }

    private fun productJsonBuild(): ProductJson {
        val jsonProduct = ProductJson()
        jsonProduct.name = "test1"
        jsonProduct.description = "testdesc"
        jsonProduct.category = categoryRepository.findAll().iterator().next().categoryId
        return jsonProduct
    }

    private fun commentJsonBuild(product: Product) = CommentJson (
                userRepository.findAll().iterator().next().userId,
                product.productId,
                "Comment content"
    )
}