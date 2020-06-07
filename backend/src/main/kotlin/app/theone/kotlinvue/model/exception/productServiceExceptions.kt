package app.theone.kotlinvue.model.exception

import java.lang.RuntimeException

class ProductNotFoundException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(id: Int) : super("There is no such product with id=$id")

    constructor(message: String, cause: Throwable) : super(message, cause)
}