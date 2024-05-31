package heispirate.cattower.adminTest

import heispirate.cattower.domain.admin.model.Admin
import heispirate.cattower.domain.admin.model.Role
import heispirate.cattower.domain.admin.repository.AdminRepository
import heispirate.cattower.domain.admin.service.AdminServiceImpl
import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import heispirate.cattower.domain.petProfile.model.Gender
import heispirate.cattower.domain.petProfile.model.PetProfile
import heispirate.cattower.domain.petProfile.repository.PetProfileRepository
import heispirate.cattower.domain.post.model.Post
import heispirate.cattower.domain.post.model.SubCategory
import heispirate.cattower.domain.post.repository.PostRepository
import heispirate.cattower.infra.category.Category
import java.time.LocalDateTime
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test1")
class AdminRoleTest @Autowired constructor(
    private val adminRepository: AdminRepository,
    private val postRepository: PostRepository,
    private val mainUserRepository: MainUserRepository,
    private val petProfileRepository: PetProfileRepository,
) {

    private val adminService = AdminServiceImpl(adminRepository,mainUserRepository)

    @BeforeEach
    fun setUp() {
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
        val savedMainUser1 = mainUserRepository.saveAndFlush(mainUser1)

        val petProfile1 = PetProfile(
            aboutMe = "test",
            address = null,
            age = 1,
            birthDay = LocalDateTime.now(),
            bloodType = null,
            disclosure = true,
            gender = Gender.MALE,
            healthHistory = "test",
            kind = null,
            mainUser = savedMainUser1,
            name = "pet1",
            profileImageUrl = "test",
            weight = 1.0
        )
        val savedPetProfile1 = petProfileRepository.saveAndFlush(petProfile1)

        val post1 = Post(
            title = "test1",
            category = Category.DAILY_LIFE,
            content = "test1",
            imageUrl = null,
            likeCounts = 0,
            petProfile = savedPetProfile1,
            subCategory = SubCategory.GOOD_THINGS,
            writer = petProfile1.name
        )
        postRepository.saveAndFlush(post1)

        val mainUser2 = MainUser(
            todayExperience = 0,
            experience = 10,
            level = 1,
            aboutMe = null,
            email = "user2@gmail.com",
            nickname = "zzzz",
            password = "1234",
            phoneNumber = "12345",
            providerId = "kakao",
            provider = "ka"
        )
        val savedMainUser2 = mainUserRepository.saveAndFlush(mainUser2)

        val petProfile2 = PetProfile(
            aboutMe = "test",
            address = null,
            age = 1,
            birthDay = LocalDateTime.now(),
            bloodType = null,
            disclosure = true,
            gender = Gender.MALE,
            healthHistory = "test",
            kind = null,
            mainUser = savedMainUser2,
            name = "pet2",
            profileImageUrl = "test",
            weight = 1.0
        )
        val savedPetProfile2 = petProfileRepository.saveAndFlush(petProfile2)

        val post2 = Post(
            title = "test2",
            category = Category.INFORMATION,
            content = "test2",
            imageUrl = null,
            likeCounts = 0,
            petProfile = savedPetProfile2,
            subCategory = SubCategory.GOOD_THINGS,
            writer = petProfile2.name
        )
        postRepository.saveAndFlush(post2)

        val dailyLifeManager = Admin(
            mainUser = savedMainUser1,
            role = Role.DAILY_LIFE_MANAGER
        )
        adminRepository.saveAndFlush(dailyLifeManager)

        val admin = Admin(
            mainUser = savedMainUser2,
            role = Role.ADMIN
        )
        adminRepository.saveAndFlush(admin)
    }

    @Test
    fun `일상 카테고리 관리자일 경우 다른 사용자의 정보 게시물을 삭제할 시 Exception이 정상 작동하는지 테스트`() {
        // given
        val email = "user1@gmail.com"
        val post = postRepository.findAll().last()
        val petProfile = petProfileRepository.findAll().first()

        // when & then
        assertThrows<IllegalStateException> {
            adminService.checkAdminTest(email, post, petProfile)
        }
    }

    @Test
    fun `일상 카테고리 관리자일 경우 다른 사용자의 일상 게시물을 삭제할 수 있는지 테스트`() {
        // given
        val email = "user1@gmail.com"
        val post = postRepository.findAll().first()
        val petProfile = petProfileRepository.findAll().first()

        // when
        adminService.checkAdminTest(email, post, petProfile)

        // then
        val deletedPost = postRepository.findById(post.id!!)
        assertTrue(deletedPost.isPresent)
    }

    @Test
    fun `Role이 Admin일 경우 모든 게시물을 삭제할 수 있는지 테스트`() {
        // given
        val email = "user2@gmail.com"
        val post = postRepository.findAll().first()
        val petProfile = petProfileRepository.findAll().first()

        // when
        adminService.checkAdminTest(email, post, petProfile)

        // then
        val deletedPost = postRepository.findById(post.id!!)
        assertTrue(deletedPost.isPresent)
    }
}