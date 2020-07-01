package app.theone.kotlinvue.model.repository

import app.theone.kotlinvue.model.data.jpa.Role
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface RoleRepository : CrudRepository<Role, Int> {

    @Query("select role from roles role where value = 'ROLE_USER'")
    fun defaultRole() : Role

    @Query("select role from roles role where value = :value")
    fun findRoleIdByValue(@Param("value") value: String): Optional<Role>
}