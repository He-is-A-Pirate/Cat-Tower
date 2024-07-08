package heispirate.cattower.domain.sms.service

import heispirate.cattower.domain.sms.model.SMS
import heispirate.cattower.domain.sms.repository.SMSRepository
import kotlin.random.Random
import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.response.SingleMessageSentResponse
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.stereotype.Service

@Service
class SMSServiceImpl(
    private val messageService: DefaultMessageService,
    private val smsRepository: SMSRepository
) : SMSService {

    override fun sendOne(to: String, text: String): SingleMessageSentResponse? {
        val message = Message(
            from = "01065412527",
            to = to,
            text = text
        )
        val response = messageService.sendOne(SingleMessageSendingRequest(message))
        println(response)

        if (response != null) {
            val sms = SMS(
                text = text,
                phoneNumber = to
            )
            smsRepository.save(sms)
        }

        return response
    }

    override fun sendVerificationCode(to: String): SingleMessageSentResponse? {
        val verificationCode = Random.nextInt(100000, 999999).toString()
        val messageText = "인증 코드는 [$verificationCode]입니다"
        return sendOne(to, messageText)
    }
}
