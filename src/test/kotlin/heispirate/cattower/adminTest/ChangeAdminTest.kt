package heispirate.cattower.adminTest

import heispirate.cattower.domain.admin.dto.AdminRequestDTOver1
import heispirate.cattower.domain.admin.model.Admin
import heispirate.cattower.domain.admin.model.Role
import heispirate.cattower.domain.admin.repository.AdminRepository
import heispirate.cattower.domain.admin.service.AdminServiceImpl
import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test1")
class ChangeAdminTest @Autowired constructor(
    private val adminRepository: AdminRepository,
    private val mainUserRepository: MainUserRepository,
) {

    private val adminService = AdminServiceImpl(adminRepository, mainUserRepository)

    @Test
    fun `어드민이 아닐 경우 예외를 정상적으로 throw 하는지 테스트`() {
        // given
        val mainUser1 = MainUser(
            todayExperience = 0,
            experience = 10,
            level = 1,
            aboutMe = null,
            email = "user1@gmail.com",
            nickname = "user1",
            password = "1234",
            phoneNumber = "12345",
            providerId = "kakao",
            provider = "ka"
        )
        mainUserRepository.saveAndFlush(mainUser1)

        val admin = Admin(role = Role.SHARING_MANAGER, mainUser = mainUser1)
        adminRepository.saveAndFlush(admin)

        val request = AdminRequestDTOver1(role = Role.INFORMATION_MANAGER, userEmail = mainUser1.email, adminEmail = mainUser1.email)

        // when & then
        assertThrows<IllegalStateException> {
            adminService.changeUserToAdminVersion1(request)
        }
    }

    @Test
    fun `어드민일 경우 원하는 MainUser를 INFORMATION_MANAGER 로 생성하여 저장 할 수 있는지 테스트`() {
        // given
        val newAdminUser = MainUser(
            todayExperience = 0,
            experience = 10,
            level = 1,
            aboutMe = null,
            email = "newInfoAdmin@gmail.com",
            nickname = "user1",
            password = "1234",
            phoneNumber = "12345",
            providerId = "kakao",
            provider = "ka"
        )
        val adminUser = MainUser(
            todayExperience = 0,
            experience = 10,
            level = 1,
            aboutMe = null,
            email = "admin@gmail.com",
            nickname = "user1",
            password = "1234",
            phoneNumber = "12345",
            providerId = "kakao",
            provider = "ka"
        )
        mainUserRepository.saveAllAndFlush(listOf(adminUser,newAdminUser))

        val admin = Admin(role = Role.ADMIN, mainUser = adminUser)
        adminRepository.saveAndFlush(admin)

        val request = AdminRequestDTOver1(role = Role.INFORMATION_MANAGER, userEmail = newAdminUser.email, adminEmail = adminUser.email)

        // when
        adminService.changeUserToAdminVersion1(request)

        // then
        val updatedAdmin = adminRepository.findByMainUserEmail(newAdminUser.email)
        assertEquals(Role.INFORMATION_MANAGER, updatedAdmin?.role)
    }
}