package heispirate.cattower.levelTest


import heispirate.cattower.domain.level.service.LevelServiceImpl
import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ExpTest @Autowired constructor(private val mainUserRepository: MainUserRepository) {

    private val levelService = LevelServiceImpl(mainUserRepository)

    @Test
    fun userExpIncreaseTest() {
        // given
        val userId = 1L
        val user = MainUser(
            todayExperience = 0,
            experience = 10,
            level = 1,
            aboutMe = null,
            email = "zzz",
            nickname = "zzzz",
            password = "1234",
            phoneNumber = "12345",
            providerId = "kakao",
            provider = "ka"
        )
        mainUserRepository.saveAndFlush(user)
        val expToAdd = 15

        // when
        levelService.gainExp(expToAdd, userId)
        val result = mainUserRepository.findByIdOrNull(userId) ?: throw Exception("user 미확인")

        // then
        result.experience shouldBe 25
        result.todayExperience shouldBe 15
        result.level shouldBe 3
    }


    @Test
    fun userExpIncreaseWhenTodayExpMaxTest() {
        // given
        val userId = 1L
        val user = MainUser(
            todayExperience = 195,
            experience = 200,
            level = 8,
            aboutMe = null,
            email = "zzz",
            nickname = "zzzz",
            password = "1234",
            phoneNumber = "12345",
            providerId = "kakao",
            provider = "ka"
        )
        mainUserRepository.saveAndFlush(user)
        val expToAdd = 25

        // when
        levelService.gainExp(expToAdd, userId)
        val result = mainUserRepository.findByIdOrNull(userId) ?: throw Exception("user 미확인")

        // then
        result.experience shouldBe 205
        result.todayExperience shouldBe 200
        result.level shouldBe 8
    }
}