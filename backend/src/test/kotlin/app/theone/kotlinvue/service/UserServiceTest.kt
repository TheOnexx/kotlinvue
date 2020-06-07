package app.theone.kotlinvue.service

import app.theone.kotlinvue.JPAConfig
import app.theone.kotlinvue.model.data.jpa.Role
import app.theone.kotlinvue.model.data.json.UserJson
import app.theone.kotlinvue.model.repository.RoleRepository
import app.theone.kotlinvue.model.repository.UserRepository
import app.theone.kotlinvue.model.service.UserService
import app.theone.kotlinvue.model.service.impl.UserServiceImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.AnnotationConfigContextLoader

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(
        classes = [JPAConfig::class],
        loader = AnnotationConfigContextLoader::class)
class UserServiceTest : AbstractTransactionalJUnit4SpringContextTests(){

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
        val role2 = Role (
                0,
                "New Role"
        )
        roleRepository.save(role)
    }


    @Test
    fun givenUser_whenSaved_thenUserAdded() {
        val buildUser = buildUser()

        val addUser = userService.addUser(buildUser)

        val findUserByName = userService.findUserByName(buildUser.userName!!)

        Assert.assertEquals(findUserByName.name, buildUser.userName)
        Assert.assertEquals(findUserByName.userId, addUser.userId)

    }

    @Test
    fun givenLoginInfo_whenLoginAction_thenLoggedIn() {
        val buildUser = buildUser()
        userService.addUser(buildUser)

        val login = userService.login(buildUser)

        Assert.assertEquals(login, true)
    }

    @Test
    fun givenNewUserInfo_whenUpdate_thenUserInfoUpdated() {
        val buildUser = buildUser()
        userService.addUser(buildUser)
        val test = userService.findUserByName(buildUser.userName!!)
        println("TEST $test")

        val newEmail = "updatedEmail"
        val newPassword = "updatedPassword"
        buildUser.email = newEmail
        buildUser.password = newPassword
        val role2Id = roleRepository.findAll().last().roleId
        buildUser.role = role2Id

        userService.updateUser(buildUser)

        println("TEST 2 $test" )

        val foundUpdatedUser = userService.findUserByName(buildUser.userName!!)

        Assert.assertEquals(newEmail, foundUpdatedUser.email)
        Assert.assertEquals(newPassword, foundUpdatedUser.password)
        Assert.assertEquals(role2Id, foundUpdatedUser.role?.roleId)


    }

    private fun buildUser() : UserJson = UserJson(
                0,
                "testUser",
                "testEmail",
                "testPassword",
                roleRepository.findAll().iterator().next().roleId
        )
}