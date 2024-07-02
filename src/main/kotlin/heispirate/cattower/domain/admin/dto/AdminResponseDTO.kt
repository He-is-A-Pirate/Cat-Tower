package heispirate.cattower.domain.admin.dto

import heispirate.cattower.domain.admin.model.Admin
import heispirate.cattower.domain.admin.model.Role

data class AdminResponseDTO(
    val email: String,
    val role : Role
) {
    companion object {
        fun fromAdmin(admin: Admin): AdminResponseDTO {
            return AdminResponseDTO(
                email = admin.mainUser.email,
                role = admin.role
            )
        }
    }
}
