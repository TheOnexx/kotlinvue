package app.theone.kotlinvue.controller

import app.theone.kotlinvue.model.data.json.UserJson
import app.theone.kotlinvue.model.service.UserService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
    @Autowired val userService: UserService
) {

    @PostMapping("login.do")
    fun login(@Valid @RequestBody userJson: UserJson): ResponseEntity<String> {
        try {
            val isLogin = userService.login(userJson)
            return if(isLogin) {
                ResponseEntity.ok("Logged in")
            } else {
                ResponseEntity(
                        "User not found or password is incorrect",
                        HttpStatus.BAD_REQUEST
                )
            }
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PostMapping("register.do")
    fun register(@Valid @RequestBody userJson: UserJson): ResponseEntity<*> {
        try {
            val user = userService.register(userJson)
            val newUserJson = ModelMapper().map(user, UserJson::class)
            return ResponseEntity.ok(newUserJson)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }
}