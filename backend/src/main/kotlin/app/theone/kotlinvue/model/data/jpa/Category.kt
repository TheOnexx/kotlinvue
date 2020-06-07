package app.theone.kotlinvue.model.data.jpa

import app.theone.kotlinvue.model.data.json.CategoryJson
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

@Entity(name="categories")
data class Category (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val categoryId: Int,
        var name: String,
        var description: String,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "parent_id")
        @NotFound(action = NotFoundAction.IGNORE)
        var parent: Category? = null

) {
    @OneToMany(mappedBy = "parent")
    @NotFound(action = NotFoundAction.IGNORE)
    val subCategories: MutableList<Category?> = mutableListOf()


    @OneToMany(cascade = [CascadeType.MERGE, CascadeType.PERSIST], mappedBy = "category")
    @NotFound(action = NotFoundAction.IGNORE)
    val products: MutableList<Product> = mutableListOf()

    init {
        if(parent != null) {
            parent?.subCategories?.add(this)

        }
    }

    internal fun addSubCategory(subCategory: Category) {
        subCategories.add(subCategory)
        subCategory.parent = this
    }

    fun update(categoryJson: CategoryJson, parent: Category?) {
        name = categoryJson.name!!
        description = categoryJson.description!!
        this.parent = parent
        parent?.addSubCategory(this)
    }
}
