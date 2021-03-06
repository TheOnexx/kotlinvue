package app.theone.kotlinvue.controller

import app.theone.kotlinvue.model.data.json.UserJson
import app.theone.kotlinvue.model.exception.ApiError
import app.theone.kotlinvue.model.exception.RestApiException
import app.theone.kotlinvue.model.service.UserService
import app.theone.kotlinvue.utils.asJsonString
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
    @Autowired val userService: UserService,
    @Autowired val authenticationManager: AuthenticationManager
) {

    @PostMapping("login.do")
    fun login(@Valid @RequestBody userJson: UserJson): ResponseEntity<*> {
        try {
            val isLogin = userService.login(userJson)
            return if(isLogin) {
                val authentication = authenticationManager.authenticate(
                        UsernamePasswordAuthenticationToken(userJson.username, userJson.password))
                SecurityContextHolder.getContext().authentication = authentication
                ResponseEntity.ok(UserJson(userJson.username, authentication.authorities.first().toString()))
            } else {
                ResponseEntity(
                        ApiError(
                                HttpStatus.CONFLICT,
                                "User not found or password is incorrect",
                                emptyList()
                        ).asJsonString(),
                        HttpStatus.CONFLICT
                )
            }
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.CONFLICT, e.message)
        }
    }

    @PostMapping("register.do")
    fun register(@Valid @RequestBody userJson: UserJson): ResponseEntity<*> {
        try {
            val user = userService.register(userJson)
            val newUserJson = ModelMapper().map(user, UserJson::class)
            return ResponseEntity.ok(newUserJson)
        } catch (e: Exception) {
            throw RestApiException(e.message!!, e)
        }
    }
}