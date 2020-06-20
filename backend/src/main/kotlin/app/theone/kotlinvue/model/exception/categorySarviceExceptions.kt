package app.theone.kotlinvue.model.exception

import java.lang.RuntimeException

class CategoryNotFoundException(categoryId: Int) : RestApiException("There is no such category with id=$categoryId") {
}