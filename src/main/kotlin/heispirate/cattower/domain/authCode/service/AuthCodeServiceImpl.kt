package heispirate.cattower.domain.authCode.service

import heispirate.cattower.domain.authCode.dto.AuthResponseDTO
import heispirate.cattower.domain.authCode.repository.AuthCodeRepository
import heispirate.cattower.infra.email.EmailUtility
import jakarta.transaction.Transactional
import java.time.LocalDateTime
import org.springframework.stereotype.Service

@Service
class AuthCodeServiceImpl(
    private val authCodeRepository: AuthCodeRepository,
    private val emailUtility: EmailUtility
) : AuthCodeService {
    @Transactional
    override fun sendAuthEmail(email: String): AuthResponseDTO {
        emailUtility.requestAuthCode(email)
        val message = "이메일 전송완료"
        return AuthResponseDTO(
            message = message
        )
    }
    @Transactional
    override fun verifyCode(email: String,code: String): AuthResponseDTO {
        val authCode = authCodeRepository.findByEmailAndAvailableIsTrue(email)?: throw Exception("유효한 인증코드가 없습니다")
        val now = LocalDateTime.now()
        val verifyMessage = "인증이 완료되었습니다"

        return if (authCode.code == code && authCode.expirationTime > now){
            authCode.available = false
            AuthResponseDTO(
                message = verifyMessage
            )
        }else{
            throw Exception("인증코드 불일치")
        }


    }
    @Transactional
    override fun deleteAuthCode(zeroHour: LocalDateTime) {
       authCodeRepository.deleteByExpirationTime(zeroHour)
    }
}