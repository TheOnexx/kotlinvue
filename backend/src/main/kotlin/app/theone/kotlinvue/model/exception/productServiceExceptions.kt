package app.theone.kotlinvue.model.exception

import java.lang.RuntimeException

class ProductNotFoundException : RuntimeException {
    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}