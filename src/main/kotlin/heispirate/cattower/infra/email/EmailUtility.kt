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
) {

    fun sendEmail(email:String, subject: String,text:String){

        val message = SimpleMailMessage()
        message.setTo(email)
        message.setSubject(" $subject ")
        message.setText(" $text ")
        message.setFrom("rlaqhtjd8934@gmaicl.com")
        javaMailSender.send(message)


    }
}


