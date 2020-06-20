package app.theone.kotlinvue.model.exception

import java.lang.RuntimeException

open class RestApiException : RuntimeException {
    constructor(throwable: Throwable) : super(throwable)
    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}