package heispirate.cattower.domain.admin.service

import heispirate.cattower.domain.admin.dto.AdminRequestDTOver1
import heispirate.cattower.domain.admin.dto.AdminResponseDTO
import heispirate.cattower.infra.category.Category

interface AdminService {
    fun checkAdmin(email: String, categorySelect: Category): Boolean

    fun changeUserToAdminVersion1(request: AdminRequestDTOver1): AdminResponseDTO

    fun signInAdmin()
}