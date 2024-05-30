package heispirate.cattower.domain.admin.service

import heispirate.cattower.infra.category.Category

interface AdminService {
    fun checkAdmin(email: String, categorySelect: Category): Boolean
}