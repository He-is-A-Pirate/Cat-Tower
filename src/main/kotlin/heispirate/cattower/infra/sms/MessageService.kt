package heispirate.cattower.infra.sms

import net.nurigo.sdk.message.response.SingleMessageSentResponse

interface MessageService {
    fun sendOne(from: String, to: String, text: String): SingleMessageSentResponse?
}