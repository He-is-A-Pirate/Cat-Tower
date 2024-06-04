package heispirate.cattower.domain.admin.dto

import heispirate.cattower.domain.admin.model.Role

data class AdminRequestDTOver1(
    val role: Role,
    val adminEmail: String,
    val userEmail : String,
)
