package app.theone.kotlinvue.model.repository

import app.theone.kotlinvue.model.data.jpa.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, Int> {

    @Query("select user from users user where name = :name")
    fun findUserByName(@Param("name") name: String) : Optional<User>
}