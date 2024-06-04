package heispirate.cattower.infra.sms

import net.nurigo.sdk.message.model.Message
import net.nurigo.sdk.message.request.SingleMessageSendingRequest
import net.nurigo.sdk.message.response.SingleMessageSentResponse
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl @Autowired constructor(
    private val messageService: DefaultMessageService
) : MessageService {

    override fun sendOne(from: String, to: String, text: String): SingleMessageSentResponse? {
        val message = Message(
            from = from,
            to = to,
            text = text
        )
        val response = messageService.sendOne(SingleMessageSendingRequest(message))
        println(response)
        return response
    }
}