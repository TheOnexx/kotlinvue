package app.theone.kotlinvue.model.service

import app.theone.kotlinvue.model.data.jpa.User
import app.theone.kotlinvue.model.data.json.UserJson
import app.theone.kotlinvue.model.exception.RoleNotFoundException
import app.theone.kotlinvue.model.exception.UserAlreadyExistException
import app.theone.kotlinvue.model.exception.UserNotFoundException
import app.theone.kotlinvue.model.repository.RoleRepository
import app.theone.kotlinvue.model.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val roleRepository: RoleRepository
) {

    @Transactional
    fun addUser(userJson: UserJson) {
        val foundUser = userRepository.findUserByName(userJson.userName!!)

        foundUser.ifPresent {
            throw UserAlreadyExistException("User with name [${it.name}] is already present")
        }

        val role = roleRepository.findById(userJson.role!!)

        role.orElseThrow {
            throw RoleNotFoundException("There is no role with id [${userJson.role}]")
        }

        val newUser = User(
                0,
                userJson.userName!!,
                userJson.email!!,
                userJson.password!!
        )
        newUser.role = role.get()

    }

    fun updateUser(userJson: UserJson) {
        val foundUser = findUserByName(userJson.userName!!)

        val role = roleRepository.findById(userJson.role!!)

        role.orElseThrow {
            throw RoleNotFoundException("There is no role with id [${userJson.role}]")
        }

        foundUser.update(userJson)
        foundUser.role = role.get()

        userRepository.save(foundUser)

    }

    fun login(userJson: UserJson) : Boolean {

        val foundUser = userRepository.findUserByName(userJson.userName!!)
        if(!foundUser.isPresent) return false
        val user = foundUser.get()
        if(user.password == userJson.password) {
            return true
        }
        return false
    }

    fun findUserByName(userName: String): User = userRepository.findUserByName(userName).orElseThrow {
        throw UserNotFoundException("There is no user [${userName}] in system")
    }
}