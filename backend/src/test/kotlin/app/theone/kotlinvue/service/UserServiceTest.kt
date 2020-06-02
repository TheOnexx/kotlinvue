package app.theone.kotlinvue.service

import app.theone.kotlinvue.JPAConfig
import app.theone.kotlinvue.model.data.jpa.Role
import app.theone.kotlinvue.model.data.json.UserJson
import app.theone.kotlinvue.model.repository.RoleRepository
import app.theone.kotlinvue.model.repository.UserRepository
import app.theone.kotlinvue.model.service.UserService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.AnnotationConfigContextLoader

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(
        classes = [JPAConfig::class],
        loader = AnnotationConfigContextLoader::class)
class UserServiceTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var userService: UserService


    @Before
    fun setUp (){
        val role = Role(
                0,
                "DefaultUser"
        )
        roleRepository.save(role)
    }


    @Test
    fun givenUser_whenSaved_thenUserAdded() {
        val buildUser = buildUser()

        userService.addUser(buildUser)

        val findUserByName = userService.findUserByName(buildUser.userName!!)

        Assert.assertEquals(findUserByName.name, buildUser.userName)

        println("role: ${findUserByName.role}")
    }

    private fun buildUser() : UserJson = UserJson(
                0,
                "testUser",
                "testEmail",
                "testPassword",
                roleRepository.findAll().iterator().next().roleId
        )
}