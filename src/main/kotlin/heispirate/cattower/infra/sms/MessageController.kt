package heispirate.cattower.infra.sms

import net.nurigo.sdk.message.response.SingleMessageSentResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MessageController @Autowired constructor(
    private val messageService: MessageService
) {

    @PostMapping("/send-one")
    fun sendOne(): SingleMessageSentResponse? {
        val from = "발신번호 입력"
        val to = "수신번호 입력"
        val text = "한글 45자, 영자 90자 이하 입력되면 자동으로 SMS타입의 메시지가 추가됩니다."
        return messageService.sendOne(from, to, text)
    }
}
