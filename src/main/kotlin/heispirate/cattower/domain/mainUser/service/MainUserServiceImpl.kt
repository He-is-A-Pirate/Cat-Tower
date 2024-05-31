package heispirate.cattower.domain.mainUser.service

import heispirate.cattower.domain.mainUser.dto.MainUserResponseDTO
import heispirate.cattower.domain.mainUser.dto.SingUpRequestDTO
import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MainUserServiceImpl(
    private val mainUserRepository :MainUserRepository
) : MainUserService {

    @Transactional
    override fun signUp(signUpRequestDTO: SingUpRequestDTO){
        if(mainUserRepository.existsByEmail(signUpRequestDTO.email)) throw Exception("이미 가입된 이메일입니다.")

        val user = MainUser(
            nickname = signUpRequestDTO.nickname,
            email = signUpRequestDTO.email,
            password = signUpRequestDTO.password,
            aboutMe = signUpRequestDTO.aboutMe,
            phoneNumber = signUpRequestDTO.phoneNumber,
            todayExperience = 0,
            experience = 0,
            level = 1,
            provider= null,
            providerId = null
        )
        mainUserRepository.save(user)
    }
}
//로그인할때 그냥 경험치증가로직 가져와서 숫자만 만져주기 끝