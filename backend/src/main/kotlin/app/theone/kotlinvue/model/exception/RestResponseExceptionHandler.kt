package app.theone.kotlinvue.model.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class RestResponseExceptionHandler {

    @ExceptionHandler(value = [RestApiException::class])
    fun handleExceptionInternal(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        val errorApi = ApiError(
                HttpStatus.CONFLICT,
                ex.message!!,
                ex.javaClass.name

        )
        return ResponseEntity(errorApi, HttpStatus.CONFLICT)
    }
}