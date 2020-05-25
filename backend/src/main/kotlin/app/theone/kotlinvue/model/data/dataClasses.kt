package app.theone.kotlinvue.model.data

import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

@Entity(name = "products")
data class Product (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val productId: Int,

        val name: String,
        val description: String,

        @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id", nullable = false)
        val category: Category,

        @OneToMany(mappedBy = "product")
        @NotFound(action = NotFoundAction.IGNORE)
        val comments: List<Comment>?
)

@Entity(name="categories")
data class Category (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val categoryId: Int,
        val name: String,
        val description: String,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "parent_id")
        @NotFound(action = NotFoundAction.IGNORE)
        val parent: Category?,

        @OneToMany(mappedBy = "parent")
        @NotFound(action = NotFoundAction.IGNORE)
        val subCategories: List<Category>?,

        @OneToMany(mappedBy = "category")
        @NotFound(action = NotFoundAction.IGNORE)
        val products: List<Product>?
)

@Entity(name="roles")
data class Role (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val roleId: Int,

        val value: String,

        @OneToMany(mappedBy = "role")
        val users: List<User>
)

@Entity(name="users")
data class User (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val userId: Int,

        val name: String,
        val email: String,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "role")
        val role: Role,
        val password: String,

        @OneToMany(mappedBy = "user")
        @NotFound(action = NotFoundAction.IGNORE)
        val comments: List<Comment>?,

        @OneToMany(mappedBy = "user")
        @NotFound(action = NotFoundAction.IGNORE)
        val orders: List<Order>?
)

@Entity(name="users")
data class Comment (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val commentId: Int,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "product_id")
        val product: Product,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "user_id")
        val user: User,

        val content: String
)

@Entity(name="statuses")
data class Status (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val statusId: Int,

        val value: String
)

@Entity(name="orders")
data class Order (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val orderId: Int,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "user_id")
        val user: User,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "status")
        val status: Status,

        val total: Int
)

