package app.theone.kotlinvue.model.service

import app.theone.kotlinvue.model.data.jpa.User
import app.theone.kotlinvue.model.data.json.UserJson

interface UserService {
    fun addUser(userJson: UserJson): User
    fun updateUser(userJson: UserJson): User
    fun login(userJson: UserJson) : Boolean
    fun findUserByName(userName: String): User
    fun register(userJson: UserJson): User
}