package heispirate.cattower.domain.admin.dto

import heispirate.cattower.domain.mainUser.model.MainUser

data class AdminResponseDTO(
    val email: String
) {
    companion object {
        fun fromMainUser(user: MainUser): AdminResponseDTO {
            return AdminResponseDTO(
                email = user.email
            )
        }
    }
}
