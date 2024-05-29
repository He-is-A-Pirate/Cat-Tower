package heispirate.cattower.domain.admin.service

import heispirate.cattower.domain.admin.model.Role
import heispirate.cattower.domain.admin.repository.AdminRepository
import heispirate.cattower.domain.petProfile.model.PetProfile
import heispirate.cattower.domain.post.model.Post
import heispirate.cattower.infra.category.Category
import heispirate.cattower.infra.category.CategoryInterface
import org.springframework.stereotype.Service

@Service
class AdminServiceImpl(private val adminRepository: AdminRepository) : AdminService {
    override fun checkAdmin(email: String, categorySelect: CategoryInterface): Boolean {
        val admin = adminRepository.findByMainUserEmail(email) ?: throw IllegalArgumentException("이메일을 찾을수 없습니다")
        return when(admin.role) {
            Role.ADMIN -> true
            Role.DAILY_LIFE_MANAGER -> categorySelect.category == Category.DAILY_LIFE
            Role.INFORMATION_MANAGER -> categorySelect.category == Category.INFORMATION
            Role.QUESTION_MANAGER -> categorySelect.category == Category.QUESTION
            Role.SHARING_MANAGER -> categorySelect.category == Category.SHARING
        }
    }

    // 예시 적용을 위한 펫 프로필 확인
    private fun userCheck(post: Post,petProfile: PetProfile): Boolean {
        return post.petProfile.id == petProfile.id
    }

    // 적용 예시
    private fun checkAdminExample(email: String, post: Post, petProfile: PetProfile) {
        if (checkAdmin(email,post)) {
            // Post 삭제 로직
        } else if (userCheck(post, petProfile)) {
            // Post 삭제 로직
        } else {
            throw IllegalStateException("삭제할 권한이 없습니다.")
        }
    }

}


