package app.theone.kotlinvue.model.data.jpa

import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

@Entity(name = "products")
data class Product (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_id")
        val productId: Int,

        val name: String,
        val description: String,

        @ManyToOne(cascade = [CascadeType.REFRESH], fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id", nullable = false)
        val category: Category
) {

    @OneToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE], mappedBy = "product")
    @NotFound(action = NotFoundAction.IGNORE)
    val comments: MutableList<Comment> = mutableListOf()

    init {
        category.products.add(this)
    }

    fun addComment(comment: Comment) {
        comments.add(comment)
    }

}
