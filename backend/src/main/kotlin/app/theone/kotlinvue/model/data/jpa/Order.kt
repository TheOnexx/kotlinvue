package app.theone.kotlinvue.model.data.jpa


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

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "status")
        val status: Status,

        val total: Int,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "product_orders",
                joinColumns = [JoinColumn(name="product_id")],
                inverseJoinColumns = [JoinColumn(name="order_id")]
        )
        val orders: MutableList<Order> = mutableListOf()
)