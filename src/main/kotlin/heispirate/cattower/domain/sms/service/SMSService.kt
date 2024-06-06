package heispirate.cattower.domain.sms.service

import net.nurigo.sdk.message.response.SingleMessageSentResponse

interface SMSService {
    fun sendOne(to: String, text: String): SingleMessageSentResponse?

    fun sendVerificationCode(to: String): SingleMessageSentResponse?
}