package heispirate.cattower.petProfileTest

import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import heispirate.cattower.domain.petProfile.dto.PetProfileRequestDTO
import heispirate.cattower.domain.petProfile.model.Gender
import heispirate.cattower.domain.petProfile.model.PetProfile
import heispirate.cattower.domain.petProfile.repository.PetProfileRepository
import heispirate.cattower.domain.petProfile.service.PetProfileServiceImpl
import heispirate.cattower.exception.ModelNotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PetProfileTest @Autowired constructor(
    private val mainUserRepository: MainUserRepository,
    private val petProfileRepository: PetProfileRepository

) {
    private val petProfileService = PetProfileServiceImpl(mainUserRepository, petProfileRepository)

    @BeforeEach
    fun setUp() {
        val mainUser1 = MainUser(
            todayExperience = 10,
            experience = 10,
            level = 1,
            aboutMe = null,
            email = "user1@gmail.com",
            nickname = "user1",
            password = "1234",
            phoneNumber = "12345",
            providerId = "1",
            provider = "kakao"
        )
        val savedMainUser1 = mainUserRepository.saveAndFlush(mainUser1)

        val petProfile1 = PetProfile(
            aboutMe = "test",
            address = null,
            age = 1,
            birthDay = LocalDateTime.of(2020, 5, 15, 0, 0),
            bloodType = null,
            gender = Gender.MALE,
            healthHistory = "test",
            kind = null,
            mainUser = savedMainUser1,
            name = "pet1",
            profileImageUrl = "test",
            weight = 1.0
        )
        petProfileRepository.saveAndFlush(petProfile1)

        val petProfile2 = PetProfile(
            aboutMe = "test",
            address = null,
            age = 1,
            birthDay = LocalDateTime.of(2020, 5, 15, 0, 0),
            bloodType = null,
            gender = Gender.MALE,
            healthHistory = "test",
            kind = null,
            mainUser = savedMainUser1,
            name = "pet2",
            profileImageUrl = "test",
            weight = 1.0
        )
        petProfileRepository.saveAndFlush(petProfile2)

        val petProfile3 = PetProfile(
            aboutMe = "test",
            address = null,
            age = 1,
            birthDay = LocalDateTime.of(2020, 5, 15, 0, 0),
            bloodType = null,
            gender = Gender.MALE,
            healthHistory = "test",
            kind = null,
            mainUser = savedMainUser1,
            name = "pet3",
            profileImageUrl = "test",
            weight = 1.0
        )
        petProfileRepository.saveAndFlush(petProfile3)


        val mainUser2 = MainUser(
            todayExperience = 10,
            experience = 10,
            level = 1,
            aboutMe = null,
            email = "user2@gmail.com",
            nickname = "user2",
            password = "1234",
            phoneNumber = "12345",
            providerId = "2",
            provider = "kakao"
        )
        val savedMainUser2 = mainUserRepository.saveAndFlush(mainUser2)

        val petProfile4 = PetProfile(
            aboutMe = "test",
            address = null,
            age = 1,
            birthDay = LocalDateTime.of(2020, 5, 15, 0, 0),
            bloodType = null,
            gender = Gender.MALE,
            healthHistory = "test",
            kind = null,
            mainUser = savedMainUser2,
            name = "pet4",
            profileImageUrl = "test",
            weight = 1.0
        )
        petProfileRepository.saveAndFlush(petProfile4)

        val mainUser3 = MainUser(
            todayExperience = 10,
            experience = 10,
            level = 1,
            aboutMe = null,
            email = "user3@gmail.com",
            nickname = "user3",
            password = "1234",
            phoneNumber = "12345",
            providerId = "3",
            provider = "kakao"
        )
        mainUserRepository.saveAndFlush(mainUser3)
    }


    @Test
    fun `펫 프로필의 갯수가 3개인 mainUser가 새로운 펫 프로필 생성을 시도할 때  Exception이 정상 작동하는지 확인`() {
        // GIVEN
        val userId = 1L
        val request = PetProfileRequestDTO(
            name = "무치",
            gender = Gender.MALE,
            birthDay = LocalDateTime.of(2020, 5, 15, 0, 0),
            age = 4,
            kind = "코숏",
            address = "서울",
            aboutMe = "나 귀엽지",
            bloodType = "A+",
            weight = 5.8,
            healthHistory = "건강함",
            profileImageUrl = "https://example.com/무치.jpg",
        )

        // WHEN & THEN
        shouldThrow<Exception> {petProfileService.createPetProfile(userId, request)}
        petProfileRepository.findAll().filter { it.mainUser.id == 1L }.let { it.size shouldBe 3L }
    }


    @Test
    fun `유효한 userId와 petProfileRequestDTO 제공 시 새로운 펫 프로필이 정상적으로 생성되는지 확인`() {
        // GIVEN
        val userId = 2L
        val request = PetProfileRequestDTO(
            name = "무치",
            gender = Gender.MALE,
            birthDay = LocalDateTime.of(2020, 5, 15, 0, 0),
            age = 4,
            kind = "코숏",
            address = "서울",
            aboutMe = "나 귀엽지",
            bloodType = "A+",
            weight = 5.8,
            healthHistory = "건강함",
            profileImageUrl = "https://example.com/무치.jpg",
        )

        // WHEN
        val createdPetProfile = petProfileService.createPetProfile(userId, request)

        // THEN
        createdPetProfile.name shouldBe "무치"
        createdPetProfile.gender shouldBe Gender.MALE
        createdPetProfile.birthday shouldBe LocalDateTime.of(2020, 5, 15, 0, 0)
        createdPetProfile.age shouldBe 4
        createdPetProfile.kind shouldBe "코숏"
        createdPetProfile.address shouldBe "서울"
        createdPetProfile.aboutMe shouldBe "나 귀엽지"
        createdPetProfile.bloodType shouldBe "A+"
        createdPetProfile.weight shouldBe 5.8
        createdPetProfile.healthHistory shouldBe "건강함"
        createdPetProfile.profileImageUrl shouldBe "https://example.com/무치.jpg"

    }

    @Test
    fun `유효한 userId와 유효한 petId를 제공 시 해당 펫 프로필이 정상적으로 조회되는지 확인`() {
        //GIVEN
        val userId = 1L
        val petId = 1L

        //WHEN
        val foundPetProfile = petProfileService.getPetProfile(userId, petId)

        //THEN
        foundPetProfile.name shouldBe "pet1"
        foundPetProfile.gender shouldBe Gender.MALE
        foundPetProfile.birthday shouldBe LocalDateTime.of(2020, 5, 15, 0, 0)
        foundPetProfile.age shouldBe 1
        foundPetProfile.kind shouldBe null
        foundPetProfile.address shouldBe null
        foundPetProfile.aboutMe shouldBe "test"
        foundPetProfile.bloodType shouldBe null
        foundPetProfile.weight shouldBe 1.0
        foundPetProfile.healthHistory shouldBe "test"
        foundPetProfile.profileImageUrl shouldBe "test"
    }

    @Test
    fun `존재하지 않거나 삭제된 펫 Id로 조회할 때 ModelNotFoundException이 발생하는 지 확인` () {
        //GIVEN
        val userId = 1L
        val petId = 5L

        //WHEN & THEN
        shouldThrow<ModelNotFoundException> { petProfileService.getPetProfile(userId, petId) }
        petProfileRepository.findByIdAndDeletedAtIsNull(petId).let { it.shouldBeNull() }
    }

    @Test
    fun `특정 유저의 모든 펫 프로필이 리스트 형태로 반환되는지 확인`() {
        //GIVEN
        val userId = 1L

        //WHEN
        val foundPetList = petProfileService.getUsersPetProfileList(userId)

        //THEN
        foundPetList.size shouldBe 3
        foundPetList.map {it.name} shouldBe listOf("pet1", "pet2", "pet3")

    }

    @Test
    fun `특정 유저가 펫 프로필을 하나도 가지고 있지 않을 경우 펫 리스트 조회 시 빈 리스트가 반환되는지 확인`() {
        //GIVEN
        val userId = 3L

        //WHEN
        val foundPetList = petProfileService.getUsersPetProfileList(userId)
        println(foundPetList)

        //THEN
        foundPetList.size shouldBe 0
        foundPetList shouldBe emptyList()
    }

    @Test
    fun `본인의 펫 프로필을 수정할 때 펫 프로필 정보가 정확히 수정, 반환되는지 확인`() {
        val userId = 1L
        val petId = 1L
        val request = PetProfileRequestDTO(
            name = "updatedPet",
            gender = Gender.FEMALE,
            birthDay = LocalDateTime.of(2019, 6, 1, 0, 0),
            age = 5,
            kind = "시베리안",
            address = "부산",
            aboutMe = "업데이트된 정보",
            bloodType = "B+",
            weight = 6.0,
            healthHistory = "건강함",
            profileImageUrl = "https://example.com/updatedPet.jpg"
        )

        //WHEN
        val updatedPetProfile = petProfileService.updatePetProfile(userId, petId, request)

        //THEN
        updatedPetProfile.name shouldBe "updatedPet"
        updatedPetProfile.gender shouldBe Gender.FEMALE
        updatedPetProfile.birthday shouldBe LocalDateTime.of(2019, 6, 1, 0, 0)
        updatedPetProfile.age shouldBe 5
        updatedPetProfile.kind shouldBe "시베리안"
        updatedPetProfile.address shouldBe "부산"
        updatedPetProfile.aboutMe shouldBe "업데이트된 정보"
        updatedPetProfile.bloodType shouldBe "B+"
        updatedPetProfile.weight shouldBe 6.0
        updatedPetProfile.healthHistory shouldBe "건강함"
        updatedPetProfile.profileImageUrl shouldBe "https://example.com/updatedPet.jpg"

    }

    @Test
    fun `다른 유저의 펫 프로필을 수정하려고 할 때 Exception이 발생되는지 확인`() {
        //GIVEN
        val userId = 2L
        val petId = 1L
        val request = PetProfileRequestDTO(
            name = "updatedPet",
            gender = Gender.FEMALE,
            birthDay = LocalDateTime.of(2019, 6, 1, 0, 0),
            age = 5,
            kind = "시베리안",
            address = "부산",
            aboutMe = "업데이트된 정보",
            bloodType = "B+",
            weight = 6.0,
            healthHistory = "건강함",
            profileImageUrl = "https://example.com/updatedPet.jpg"
        )

        //WHEN & THEN
        shouldThrow<Exception> { petProfileService.updatePetProfile(userId, petId, request) }

    }

    @Test
    fun `본인의 펫 프로필을 삭제할 때, 해당 펫 프로필의 deletedAt 필드가 현재 시간으로 설정되는지 확인`() {
        //GIVEN
        val userId = 1L
        val petId = 1L

        //WHEN
        petProfileService.deletePetProfile(userId, petId)

        //THEN
        val deletedPetProfile = petProfileRepository.findById(petId).get()
        deletedPetProfile.deletedAt.shouldNotBeNull()
        deletedPetProfile.deletedAt!!.isBefore(LocalDateTime.now()).shouldBeTrue()
    }

    @Test
    fun `다른 유저의 펫 프로필을 삭제하려고 할 때 Exception이 발생하는지 확인`() {
        //GIVEN
        val userId = 2L
        val petId = 1L

        //WHEN & THEN
        shouldThrow<Exception> { petProfileService.deletePetProfile(userId, petId) }
    }
}