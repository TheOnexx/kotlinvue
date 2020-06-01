package app.theone.kotlinvue.model.repository

import app.theone.kotlinvue.model.data.jpa.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Int> {}