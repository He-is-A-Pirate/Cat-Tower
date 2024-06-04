package heispirate.cattower.domain.sms.controller

import heispirate.cattower.domain.sms.dto.SendSMSRequestDTO
import heispirate.cattower.domain.sms.service.SMSService
import net.nurigo.sdk.message.response.SingleMessageSentResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class SMSController @Autowired constructor(
    private val smsService: SMSService
) {

    @PostMapping("/send-one")
    fun sendOne(@RequestBody request: SendSMSRequestDTO): SingleMessageSentResponse? {
        return smsService.sendOne(request.to, request.text)
    }
}
