package app.theone.kotlinvue.service

import app.theone.kotlinvue.JPAConfig
import app.theone.kotlinvue.model.data.enums.OrderStatus
import app.theone.kotlinvue.model.data.jpa.*
import app.theone.kotlinvue.model.data.json.OrderJson
import app.theone.kotlinvue.model.repository.CategoryRepository
import app.theone.kotlinvue.model.repository.OrderRepository
import app.theone.kotlinvue.model.repository.ProductRepository
import app.theone.kotlinvue.model.repository.UserRepository
import app.theone.kotlinvue.model.service.OrderService
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
class OrderServiceTest : AbstractTransactionalJUnit4SpringContextTests(){

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var categorRepository: CategoryRepository
    
    @Autowired
    lateinit var orderService: OrderService
    
    @Autowired
    lateinit var orderRepository: OrderRepository

    @Before
    fun setUp () {
        val user = User(
                0,
                "test1",
                "test@mail",
                "password"
        )

        userRepository.save(user)


        val category = Category(
                0,
                "testCategory",
                "description",
                null
        )

        categorRepository.save(category)

        val product = Product(
                0,
                "product1",
                "product1Description",
                100,
                category
        )
        val product2 = Product(
                0,
                "product2",
                "product2Description",
                200,
                category
        )

        productRepository.save(product)
        productRepository.save(product2)
    }

    @Test
    fun givenNewOrderInfo_whenSaved_thenAllRelatedDataSaved() {
        val newOrder = buildOrder()
        
        orderService.addOrder(newOrder)

        val next = orderRepository.findAll().iterator().next()
        
        Assert.assertEquals(newOrder.userId, next.user.userId)
        Assert.assertEquals(newOrder.statusId, next.status.ordinal)
        Assert.assertEquals(100, next.total)
        val productIds = next.products.map { it.productId }
        Assert.assertEquals(newOrder.orderedProducts, productIds)

        println(next)

    }

    @Test
    fun givenUserId_whenOrderByUserId_thenOrderIsFound() {
        val user = userRepository.findAll().first()

        val orders = orderService.findAllOrdersByUser(user.userId)

        Assert.assertEquals(0, orders.count())

        orderService.addOrder(buildOrder())

        val ordersAfterAdded = orderService.findAllOrdersByUser(user.userId)

        Assert.assertEquals(1, ordersAfterAdded.count())


    }

    @Test
    fun givenOrderInfo_whenSaved_thenChangedProductList() {

        val addOrder = orderService.addOrder(buildOrder())

        Assert.assertEquals(1, addOrder.products.count())

        val buildOrder = buildOrder()

        buildOrder.orderId = addOrder.orderId
        buildOrder.orderedProducts = productRepository.findAll().map { it.productId }

        val changeProductsInOrder = orderService.changeProductsInOrder(buildOrder)

        Assert.assertEquals(300, changeProductsInOrder.total)
        Assert.assertEquals(2, changeProductsInOrder.products.count())
    }

    private fun buildOrder(): OrderJson {
        val user = userRepository.findAll().iterator().next()
        val product = productRepository.findAll().iterator().next()

        return OrderJson(
                user.userId,
                OrderStatus.NEW.ordinal,
                mutableListOf(product.productId)
        )
    }

}