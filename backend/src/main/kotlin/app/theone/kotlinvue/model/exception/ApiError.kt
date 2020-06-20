package app.theone.kotlinvue.model.exception

import org.springframework.http.HttpStatus

data class ApiError(val httpStatus: HttpStatus, val message: String, val errors: List<String?>?) {
    constructor(httpStatus: HttpStatus, message: String, error: String?) : this(httpStatus, message, listOf(error))
}