package app.theone.kotlinvue.model.repository

import app.theone.kotlinvue.model.data.jpa.Role
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, Int> {

    @Query("select role from roles where value = 'ROLE_USER'")
    fun defaultRole() : Role
}