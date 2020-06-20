package app.theone.kotlinvue.model.exception

class UserAlreadyExistException(message: String) : RestApiException(message) {
}

class RoleNotFoundException(message: String) : RestApiException(message) {

}

class UserNotFoundException(message: String) : RestApiException(message)
class UserIdNotFoundException(userId: Int) : RestApiException("User id $userId is not found")