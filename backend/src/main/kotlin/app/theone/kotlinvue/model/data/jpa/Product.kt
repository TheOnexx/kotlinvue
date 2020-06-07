package app.theone.kotlinvue.model.data.jpa

import app.theone.kotlinvue.model.data.json.ProductJson
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

@Entity(name = "products")
data class Product (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_id")
        val productId: Int,

        var name: String,
        var description: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id", nullable = false)
        var category: Category
) {

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "product")
    @NotFound(action = NotFoundAction.IGNORE)
    val comments: MutableList<Comment> = mutableListOf()

    init {
        category.products.add(this)
    }

    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun update(json: ProductJson, category: Category) {
        this.name = json.name!!
        this.description = json.description!!
        this.category.products.remove(this)
        this.category = category
        this.category.products.add(this)
    }

}
