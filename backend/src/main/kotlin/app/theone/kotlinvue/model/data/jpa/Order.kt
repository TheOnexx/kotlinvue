package app.theone.kotlinvue.model.data.jpa


import app.theone.kotlinvue.model.data.enums.OrderStatus
import javax.persistence.*

@Entity(name="orders")
data class Order (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "order_id")
        val orderId: Int,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "user_id")
        val user: User,

        @Enumerated
        val status: OrderStatus,

        val total: Int,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "product_orders",
                joinColumns = [JoinColumn(name="product_id")],
                inverseJoinColumns = [JoinColumn(name="order_id")]
        )
        val products: MutableList<Product> = mutableListOf()
)