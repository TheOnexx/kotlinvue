package app.theone.kotlinvue.model.exception

class UserAlreadyExistException(message: String) : Exception(message) {
}

class RoleNotFoundException(message: String) : Exception(message) {

}

class UserNotFoundException(message: String) : Exception(message)
class UserIdNotFoundException(userId: Int) : Exception("User id $userId is not found")