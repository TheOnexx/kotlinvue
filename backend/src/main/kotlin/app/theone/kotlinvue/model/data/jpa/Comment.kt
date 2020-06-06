package app.theone.kotlinvue.model.data.jpa


import javax.persistence.*


@Entity(name="comments")
data class Comment (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val commentId: Int,


        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "user_id")
        val user: User,
        var content: String,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "product_id")
        val product: Product
)