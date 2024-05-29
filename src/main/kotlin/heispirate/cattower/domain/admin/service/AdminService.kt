package heispirate.cattower.domain.admin.service

import heispirate.cattower.infra.category.CategoryInterface

interface AdminService {
    fun checkAdmin(email: String, categorySelect: CategoryInterface): Boolean
}