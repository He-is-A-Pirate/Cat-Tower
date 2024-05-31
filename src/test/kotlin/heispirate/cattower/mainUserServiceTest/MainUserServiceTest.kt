package heispirate.cattower.mainUserServiceTest
import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import heispirate.cattower.domain.mainUser.service.MainUserServiceImpl
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import heispirate.cattower.domain.mainUser.dto.SingUpRequestDTO
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mainUserTest")
class MainUserServiceTest @Autowired constructor(
    private val mainUserRepository: MainUserRepository
){
    private val mainUserService = MainUserServiceImpl(mainUserRepository)

    @Test
    fun `이미 회원가입이 되어있는 이메일이라면 예외가 발생하는지 확인`(){

        //GIVEN
        val member = MainUser(
            nickname = "이율",
            email = "dldbf@nate.com",
            password = "1234",
            aboutMe = "하위",
            phoneNumber = "01077777777",
            todayExperience = 0,
            experience = 0,
            level = 1,
            provider = null,
            providerId = null
        )
        mainUserRepository.saveAndFlush(member)
       val signUpRequestDTO = SingUpRequestDTO(
           nickname =  "이율",
           email = "dldbf@nate.com",
           password = "1234",
           aboutMe = "하위",
           phoneNumber = "01077777777"
       )
        //WHEN&THEN
        shouldThrow<Exception> {
            mainUserService.signUp(signUpRequestDTO)
        }.let {
            it.message shouldBe "이미 가입된 이메일입니다."
        }


    }

    // 이메일이 중복되었을때 올바르게 에러를 뱉을것인가
    // 이메일 중복없이 잘 넣었    지만 null이 존재하는 요청일때 에러를 잘 뱉을것인가
    @Test
    fun `이메일 중복이 없고 값이 올바르게 있을때 (null) 이 아닐때 성공할것인가`(){
            //GIVEN
        val signUpRequestDTO = SingUpRequestDTO(
            nickname =  "김보성",
            email = "njunju777@nate.com",
            password = "1234",
            aboutMe = "하위",
            phoneNumber = "01077777777"
            )
            //WHEN
        mainUserService.signUp(signUpRequestDTO)
        val result = mainUserRepository.findByEmail(signUpRequestDTO.email)
            //THEN
        result?.nickname shouldBe "김보성"
        result?.email shouldBe "njunju777@nate.com"
        result?.password shouldBe "1234"
        result?.aboutMe shouldBe "하위"
        result?.phoneNumber shouldBe "01077777777"
    }

//        @Test
//        fun `이메일 중복없이 잘 넣었지만 null이 존재하는 요청일때 에러를 잘 뱉을것인가`(){
//            TODO(검증 미적용 검증 적용시 에러가 나는지 확인해볼것)
//        }


}