package app.theone.kotlinvue.service

import app.theone.kotlinvue.JPAConfig
import app.theone.kotlinvue.model.data.jpa.Category
import app.theone.kotlinvue.model.data.jpa.User
import app.theone.kotlinvue.model.data.json.CommentJson
import app.theone.kotlinvue.model.data.json.ProductJson
import app.theone.kotlinvue.model.repository.CategoryRepository
import app.theone.kotlinvue.model.repository.UserRepository
import app.theone.kotlinvue.model.service.ProductService
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

        val commentJson = CommentJson(
                userRepository.findAll().iterator().next().userId,
                foundProduct.productId,
                "Comment content"
        )
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

    private fun productJsonBuild(): ProductJson {
        val jsonProduct = ProductJson()
        jsonProduct.name = "test1"
        jsonProduct.description = "testdesc"
        jsonProduct.category = categoryRepository.findAll().iterator().next().categoryId
        return jsonProduct
    }
}