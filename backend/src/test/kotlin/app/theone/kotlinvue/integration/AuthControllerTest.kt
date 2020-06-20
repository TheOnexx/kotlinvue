package app.theone.kotlinvue.integration

import app.theone.kotlinvue.JPAConfig
import app.theone.kotlinvue.model.data.jpa.Role
import app.theone.kotlinvue.model.data.jpa.User
import app.theone.kotlinvue.model.data.json.UserJson
import app.theone.kotlinvue.model.repository.RoleRepository
import app.theone.kotlinvue.model.repository.UserRepository
import app.theone.kotlinvue.utils.asJsonString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(
        classes = [JPAConfig::class]
        )
@WebAppConfiguration
@EnableWebMvc
class AuthControllerTest {

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var wac: WebApplicationContext


    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()

        if(roleRepository.findAll().count() == 0) {

            val role = Role(0, "ROLE_USER")
            val user = User(
                    0,
                    "test",
                    "test@email",
                    "test_pass"
            )
            user.role = role
            roleRepository.save(role)

            userRepository.save(user)
        }
    }



        @Test
    fun givenUserLoginInfo_whenLoginDo_thenLoggedIn() {
        val userJson = UserJson(
                null,
                "test",
                null,
                "test_pass",
                null
        )

        this.mockMvc.perform(post("/auth/login.do")
                .contentType(APPLICATION_JSON)
                .content(userJson.asJsonString()))
                .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk)
                .andReturn()

    }

    @Test
    fun givenUserLoginInfo_whenLoginDo_thenErrorOnLogin() {
        val userJson = UserJson(
                null,
                "test1",
                null,
                "test_pass",
                null
        )

        this.mockMvc.perform(post("/auth/login.do")
                .contentType(APPLICATION_JSON)
                .content(userJson.asJsonString()))
                .andDo(MockMvcResultHandlers.print()).andExpect(status().isConflict)
                .andExpect(jsonPath("message").value("User not found or password is incorrect"))
                .andReturn()

    }

    @Test
    fun givenUserRegisterInfo_whenRegisterDo_thenUserAdded() {
        val userJson = UserJson(
                null,
                "newUser",
                "newEmail",
                "newPassword",
                null
        )

        this.mockMvc.perform(post("/auth/register.do")
                .contentType(APPLICATION_JSON)
                .content(userJson.asJsonString()))
                .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk)
                .andReturn()


    }

}