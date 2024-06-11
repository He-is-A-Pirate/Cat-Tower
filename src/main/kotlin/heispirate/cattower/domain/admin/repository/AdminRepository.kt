package heispirate.cattower.domain.admin.repository

import heispirate.cattower.domain.admin.model.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository: JpaRepository<Admin,Long> , CustomAdminRepository {
    fun findByMainUserEmail(email: String): Admin?
}