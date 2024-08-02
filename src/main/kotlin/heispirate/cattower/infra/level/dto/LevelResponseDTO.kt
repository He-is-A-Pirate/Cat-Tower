package heispirate.cattower.infra.level.dto

import heispirate.cattower.domain.mainUser.model.MainUser

data class LevelResponseDTO(
    val level : Int,
    val exp : Int,
) {
    companion object {
        fun fromMainUser(user : MainUser): LevelResponseDTO {
            return LevelResponseDTO(
                exp = user.experience,
                level = user.level
            )
        }
    }
}
