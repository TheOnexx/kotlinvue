package app.theone.kotlinvue.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    @Qualifier("userDetailsServiceCustom")
    lateinit var userDetailService: UserDetailsService

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailService)
    }

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
                .antMatchers("/users/self")
                    .hasRole("USER")
                .antMatchers("/", "/**")
                    .permitAll()
    }
}