package app.theone.kotlinvue

import app.theone.kotlinvue.model.data.jpa.*
import org.junit.Assert.assertTrue
import org.hibernate.cfg.Configuration
import org.hibernate.testing.junit4.BaseCoreFunctionalTestCase
import org.hibernate.testing.transaction.TransactionUtil.doInHibernate
import org.junit.Assert
import org.junit.Test
import java.io.IOException
import java.util.*

class BaseHibernateTests : BaseCoreFunctionalTestCase() {

    private val properties: Properties
        @Throws(IOException::class)
        get() {
            val properties = Properties()
            properties.load(javaClass.classLoader.getResourceAsStream("hibernate.properties"))
            return properties
        }

    override fun getAnnotatedClasses(): Array<Class<*>> {
        return arrayOf(
                User::class.java,
                Role::class.java,
                Comment::class.java,
                Order::class.java,
                Product::class.java,
                Category::class.java,
                Status::class.java
        )
    }

    override fun configure(configuration: Configuration) {
        super.configure(configuration)
        configuration.properties = properties
    }


    @Test
    fun test_User_Roles() {
        doInHibernate(({ this.sessionFactory() }), { session ->

            val role1 = Role(0, "Admin")
            val user1 = User(0,
                    "test1",
                    "test1.email",
                    "test_pass")

            user1.role = role1

            session.persist(user1)

            val foundUser = session.find(User::class.java, user1.userId)
            val foundRole = session.find(Role::class.java, role1.roleId)

            val user2 = User(0, "test2", "test2.email", "testpass")
            user2.role = role1
            session.persist(user2)

            println(foundRole.users)

            assertTrue(user1 == foundUser)
        })
    }

    @Test
    fun test_Product_Comments() {
        doInHibernate(({ this.sessionFactory() }), { session ->
            var category = Category(0, "testCat","desc")
            var product1 = Product(0, "test1", "testDesc", category)
            val user1 = User(0,
                    "test1",
                    "test1.email",
                    "test_pass")
            var comment = Comment(0, user1, "text", product1)

            session.persist(comment)

        })
    }

    @Test
    fun test_Categories_and_SubCategories() {
        doInHibernate(({ this.sessionFactory() }), { session ->

            var category = Category(0, "testCat", "desc")
            var subCat = Category(0, "subTest", "desc1", category)


            session.persist(subCat)

            val find = session.find(Category::class.java, category.categoryId)

            Assert.assertFalse(find.subCategories.isEmpty())
            Assert.assertEquals(find.subCategories, category.subCategories)

            println(find.subCategories)

        })
    }

    @Test
    fun add_Product_to_Category() {
        doInHibernate(({ this.sessionFactory() }), { session ->
            val category = Category(0, "test", "desc")

            val product = Product(0, "name", "desc", category)

        })
    }
}