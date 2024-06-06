package heispirate.cattower.domain.mainUser.dto

data class MainUserRequestDTO(
    val nickname: String,
    val password: String,
    val aboutMe: String,
    val phoneNumber: String,
)