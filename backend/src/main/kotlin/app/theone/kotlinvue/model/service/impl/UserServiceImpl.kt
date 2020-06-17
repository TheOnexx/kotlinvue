package app.theone.kotlinvue.model.service.impl

import app.theone.kotlinvue.model.data.jpa.User
import app.theone.kotlinvue.model.data.json.UserJson
import app.theone.kotlinvue.model.exception.RoleNotFoundException
import app.theone.kotlinvue.model.exception.UserAlreadyExistException
import app.theone.kotlinvue.model.exception.UserNotFoundException
import app.theone.kotlinvue.model.repository.RoleRepository
import app.theone.kotlinvue.model.repository.UserRepository
import app.theone.kotlinvue.model.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserServiceImpl(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val roleRepository: RoleRepository
) : UserService {

    @Transactional
    override fun addUser(userJson: UserJson): User {
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

        return newUser

    }

    override fun register(userJson: UserJson): User {
        val defaultRole = roleRepository.defaultRole()
        userJson.role = defaultRole.roleId

        return addUser(userJson)
    }

    override fun updateUser(userJson: UserJson): User {
        val foundUser = findUserByName(userJson.userName!!)

        val role = roleRepository.findById(userJson.role!!)

        role.orElseThrow {
            throw RoleNotFoundException("There is no role with id [${userJson.role}]")
        }

        foundUser.update(userJson)
        foundUser.role = role.get()

        return userRepository.save(foundUser)

    }

    override fun login(userJson: UserJson) : Boolean {

        val foundUser = userRepository.findUserByName(userJson.userName!!)
        if(!foundUser.isPresent) return false
        val user = foundUser.get()
        if(user.password == userJson.password) {
            return true
        }
        return false
    }

    override fun findUserByName(userName: String): User = userRepository.findUserByName(userName).orElseThrow {
        throw UserNotFoundException("There is no user [${userName}] in system")
    }
}