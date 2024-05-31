package heispirate.cattower.domain.mainUser.dto

import heispirate.cattower.domain.mainUser.model.MainUser

data class MainUserResponseDTO(
    val nickname: String,
    val email: String,
    val aboutMe: String?,
    val phoneNumber: String,
    val level: Int,
    val exp: Int
){
    companion object{
        fun fromMainUser(mainUser: MainUser):MainUserResponseDTO{
            return MainUserResponseDTO(
                nickname = mainUser.nickname,
                email = mainUser.email,
                aboutMe = mainUser.aboutMe,
                phoneNumber = mainUser.phoneNumber,
                level = mainUser.level,
                exp = mainUser.experience
            )
        }
    }
}


