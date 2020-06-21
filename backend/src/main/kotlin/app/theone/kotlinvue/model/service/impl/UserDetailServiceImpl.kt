package app.theone.kotlinvue.model.service.impl

import app.theone.kotlinvue.model.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service("userDetailsServiceCustom")
class UserDetailServiceImpl(
        @Autowired val userService: UserService
) : UserDetailsService {

    override fun loadUserByUsername(userName: String): UserDetails {
        val foundUser = userService.findUserByName(userName)

        val grantedAuthority =  SimpleGrantedAuthority(foundUser.role?.value)

        return User
                .withUsername(foundUser.name)
                .password(foundUser.password)
                .authorities(grantedAuthority)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build()
    }
}