package heispirate.cattower.domain.mainUser.dto


data class SingUpRequestDTO(
    val nickname: String,
    val email: String,
    val password: String,
    val aboutMe: String,
    val phoneNumber: String,
)
