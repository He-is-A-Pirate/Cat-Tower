package heispirate.cattower.domain.authCode.service

import heispirate.cattower.domain.authCode.dto.AuthResponseDTO
import heispirate.cattower.domain.authCode.model.AuthCode
import heispirate.cattower.domain.authCode.repository.AuthCodeRepository
import heispirate.cattower.infra.email.EmailUtility
import jakarta.transaction.Transactional
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class AuthCodeServiceImpl(
    private val authCodeRepository: AuthCodeRepository,
    private val emailUtility: EmailUtility
) : AuthCodeService {
    @Transactional
    override fun sendAuthEmail(email: String): AuthResponseDTO {
        val randomCode = generateCode(email)
        emailUtility.sendEmail(
            email = email,
            subject = "캣타워 인증 코드 입니다",
            text = "인증코드 : $randomCode"
        )
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

    private fun generateCode(email: String):String{
        val randomCode = UUID.randomUUID().toString().replace("-","").take(6)
        val expirationTime = LocalDateTime.now().plusMinutes(5)
        val authCode = authCodeRepository.findByEmailAndAvailableIsTrue(email)

        when(authCode?.requestNumber){
            null -> {
                authCodeRepository.save(
                    AuthCode(
                        code = randomCode,
                        expirationTime = expirationTime,
                        email = email,
                        requestNumber = 1,
                        available = true
                    )
                )
            }
            1 ->{
                authCode.available = false
                authCodeRepository.save(authCode)
                authCodeRepository.save(
                    AuthCode(
                        code = randomCode,
                        expirationTime = expirationTime,
                        email = email,
                        requestNumber = 2,
                        available = true
                    )
                )
            }
            2 -> {
                authCode.available = false
                authCodeRepository.save(authCode)
                authCodeRepository.save(
                    AuthCode(
                        code = randomCode,
                        expirationTime = expirationTime,
                        email = email,
                        requestNumber = 3,
                        available = true
                    )
                )
            }
            3 ->{
                throw Exception ("이메일 발송 횟수 초과입니다 익일 00시 이후에 시도해주세요")

            }

        }
        return randomCode
    }
}