package heispirate.cattower.domain.admin.service

import heispirate.cattower.domain.admin.dto.AdminRequestDTOver1
import heispirate.cattower.domain.admin.dto.AdminResponseDTO
import heispirate.cattower.domain.admin.model.Admin
import heispirate.cattower.domain.admin.model.Role
import heispirate.cattower.domain.admin.repository.AdminRepository
import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import heispirate.cattower.domain.petProfile.model.PetProfile
import heispirate.cattower.domain.post.model.Post
import heispirate.cattower.exception.ModelNotFoundException
import heispirate.cattower.infra.category.Category
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminServiceImpl(
    private val adminRepository: AdminRepository, private val mainUserRepository: MainUserRepository
) : AdminService {
    override fun checkAdmin(email: String, categorySelect: Category): Boolean {
        val admin = adminRepository.findByMainUserEmail(email) ?: throw IllegalArgumentException("이메일을 찾을수 없습니다")
        return when (admin.role) {
            Role.ADMIN -> true
            Role.DAILY_LIFE_MANAGER -> categorySelect == Category.DAILY_LIFE
            Role.INFORMATION_MANAGER -> categorySelect == Category.INFORMATION
            Role.QUESTION_MANAGER -> categorySelect == Category.QUESTION
            Role.SHARING_MANAGER -> categorySelect == Category.SHARING
        }
    }

    @Transactional
    override fun changeUserToAdminVersion1(request: AdminRequestDTOver1): AdminResponseDTO {
        val admin = adminRepository.findByMainUserEmail(request.adminEmail) ?: throw IllegalArgumentException("이메일을 찾을수 없습니다")
        if(admin.role == Role.ADMIN) {
            val user = mainUserRepository.findByEmail(request.userEmail)
            val changeToAdmin = Admin(mainUser = user!!, role = request.role)

            adminRepository.save(changeToAdmin)

            return AdminResponseDTO.fromMainUser(user)
        } else {
            throw IllegalStateException("Admin이 아닙니다")
        }
    }

    @Transactional
    override fun signInAdmin(request: AdminRequestDTOver1) {
        val admin = adminRepository.findByMainUserEmail(request.adminEmail) ?: throw IllegalArgumentException("이메일을 찾을수 없습니다")
    }

    override fun updateAdmin(email: String): AdminResponseDTO {
        TODO("Not yet implemented")
    }

    override fun deleteAdmin(email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAdmin(email: String) {
        TODO("Not yet implemented")
    }

    override fun getAllAdmin() {
        TODO("Not yet implemented")
    }

    // 예시 적용을 위한 펫 프로필 확인
    private fun userCheck(post: Post, petProfile: PetProfile): Boolean {
        return post.petProfile.id == petProfile.id
    }

    // 적용 예시
    fun checkAdminTest(email: String, post: Post, petProfile: PetProfile) {
        if (checkAdmin(email, post.category)) {
            // Post 삭제 로직
            println("Post 삭제됨: ${post.id} by Admin")
        } else if (userCheck(post, petProfile)) {
            // Post 삭제 로직
            println("Post 삭제됨: ${post.id} by User")
        } else {
            throw IllegalStateException("삭제할 권한이 없습니다.")
        }
    }

}


