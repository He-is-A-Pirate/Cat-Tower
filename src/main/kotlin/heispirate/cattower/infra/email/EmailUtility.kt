package heispirate.cattower.infra.email

import heispirate.cattower.domain.authCode.model.AuthCode
import heispirate.cattower.domain.authCode.repository.AuthCodeRepository
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class EmailUtility(
    val javaMailSender: JavaMailSender,
    val authRepository: AuthCodeRepository
) {

     fun requestAuthCode(email: String) {
         sendEmail(email)
     }


    private fun generateCode(email: String):String{
        val randomCode = UUID.randomUUID().toString().replace("-","").take(6)//랜덤코드 생성
        val expirationTime = LocalDateTime.now().plusMinutes(5)
        val authCode = authRepository.findByEmailAndAvailableIsTrue(email)

        when(authCode?.requestNumber){
            null -> {
                authRepository.save(
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
                authRepository.save(authCode)
                authRepository.save(
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
                authRepository.save(authCode)
                authRepository.save(
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

    private fun sendEmail(email:String){
        val randomCode = generateCode(email)

        val message = SimpleMailMessage()
        message.setTo(email)
        message.setSubject("캣타워 인증메일입니다")
        message.setText("인증코드: $randomCode")
        message.setFrom("rlaqhtjd8934@gmaicl.com")
        javaMailSender.send(message)


    }
}


