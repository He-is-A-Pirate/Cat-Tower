package heispirate.cattower.domain.sms.dto

data class SendSMSRequestDTO(
    val to: String,
    val text: String
)
