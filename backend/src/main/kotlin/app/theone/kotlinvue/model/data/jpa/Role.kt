package app.theone.kotlinvue.model.data.jpa

import javax.persistence.*

@Entity(name="roles")
data class Role (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="role_id")
        val roleId: Int,

        val value: String
) {

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "role")
    val users: MutableList<User> = mutableListOf()

    internal fun addUser(user: User) {
        users.add(user)
    }

    fun remove(user: User) {
        users.remove(user)
    }
}