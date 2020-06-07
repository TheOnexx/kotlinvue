package app.theone.kotlinvue.model.exception

import java.lang.RuntimeException

class CategoryNotFoundException(categoryId: Int) : RuntimeException("There is no such category with id=$categoryId") {
}