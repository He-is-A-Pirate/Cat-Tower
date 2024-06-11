package heispirate.cattower.domain.mainUser.service

import heispirate.cattower.domain.mainUser.dto.MainUserRequestDTO
import heispirate.cattower.domain.mainUser.dto.MainUserResponseDTO
import heispirate.cattower.domain.mainUser.dto.SingUpRequestDTO
import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import heispirate.cattower.exception.ModelNotFoundException
import jakarta.transaction.Transactional
import java.time.LocalDateTime
import org.springframework.data.repository.findByIdOrNull
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

    override fun getUser(userId: Long): MainUserResponseDTO {
        val user = mainUserRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("userId",userId)
        return MainUserResponseDTO.fromMainUser(user)
    }

    @Transactional
    override fun updateUser(userId: Long,mainUserRequestDTO: MainUserRequestDTO): MainUserResponseDTO {
        val user = mainUserRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("userId",userId)

        user.nickname = mainUserRequestDTO.nickname
        user.password = mainUserRequestDTO.password
        user.aboutMe = mainUserRequestDTO.aboutMe
        user.phoneNumber = mainUserRequestDTO.phoneNumber

        mainUserRepository.save(user)// 더티체크 공부 미흡
        return MainUserResponseDTO.fromMainUser(user)
    }
    @Transactional
    override fun deleteUser(userId: Long) {
        val user = mainUserRepository.findByIdAndDeletedAtIsNull(userId) ?: throw ModelNotFoundException("userId",userId)
//        if (user.deletedAt == null){
//            user.deletedAt = LocalDateTime.now()
//            mainUserRepository.save(user)//더티체크 공부 미흡
//        }else{
//            throw Exception("삭제된 아이디 입니다")
//        }
        user.deletedAt = LocalDateTime.now()
    }
}
//로그인할때 그냥 경험치증가로직 가져와서 숫자만 만져주기 끝