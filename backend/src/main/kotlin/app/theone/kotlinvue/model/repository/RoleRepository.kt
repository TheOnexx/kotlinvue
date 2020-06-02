package app.theone.kotlinvue.model.repository

import app.theone.kotlinvue.model.data.jpa.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, Int> {
}