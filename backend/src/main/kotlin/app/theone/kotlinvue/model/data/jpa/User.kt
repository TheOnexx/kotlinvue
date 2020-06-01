package app.theone.kotlinvue.model.data.jpa

import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

@Entity(name="users")
data class User (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val userId: Int,

        val name: String,
        val email: String,

        val password: String
) {
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "role")
    var role: Role? = null
        set(value) {
            field = value
            value!!.addUser(this)
        }

    @OneToMany(mappedBy = "user")
    @NotFound(action = NotFoundAction.IGNORE)
    val comments: MutableList<Comment> = mutableListOf()

    @OneToMany(mappedBy = "user")
    @NotFound(action = NotFoundAction.IGNORE)
    val orders: MutableList<Order> = mutableListOf()


    fun addComment(comment: Comment) {
        comments.add(comment)
    }
}