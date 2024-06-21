package heispirate.cattower.domain.petProfile.service

import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import heispirate.cattower.domain.petProfile.dto.PetProfileRequestDTO
import heispirate.cattower.domain.petProfile.dto.PetProfileResponseDTO
import heispirate.cattower.domain.petProfile.repository.PetProfileRepository
import heispirate.cattower.exception.ModelNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class PetProfileServiceImpl(
    private val mainUserRepository: MainUserRepository,
    private val petProfileRepository: PetProfileRepository
): PetProfileService {
    @Transactional
    override fun createPetProfile(
        userId: Long,
        request: PetProfileRequestDTO
    ): PetProfileResponseDTO {
        val mainUser = validateMainUser(userId)
        val petProfiles = petProfileRepository.findAllByMainUserAndDeletedAtIsNull(mainUser)
        if (petProfiles.size >= 3) {
            throw Exception("펫 프로필 생성은 3개를 초과할 수 없습니다.")
        }
        val newPetProfile = request.toEntity(mainUser)
        val savedPetProfile = petProfileRepository.save(newPetProfile)
        return PetProfileResponseDTO.toResponse(savedPetProfile)
    }

    @Transactional
    override fun getPetProfile(
        userId: Long,
        petId: Long
    ): PetProfileResponseDTO {
        validateMainUser(userId)
        val petProfile =
            petProfileRepository.findByIdAndDeletedAtIsNull(petId) ?: throw ModelNotFoundException("pet", petId)
        return PetProfileResponseDTO.toResponse(petProfile)
    }

    @Transactional
    override fun getUsersPetProfileList(
        userId: Long
    ): List<PetProfileResponseDTO> {
        val mainUser = validateMainUser(userId)
        val petProfiles = petProfileRepository.findAllByMainUserAndDeletedAtIsNull(mainUser)
        return petProfiles.map { PetProfileResponseDTO.toResponse(it) }
    }

    @Transactional
    override fun updatePetProfile(userId: Long, petId: Long, request: PetProfileRequestDTO
    ): PetProfileResponseDTO {
        val mainUser = validateMainUser(userId)
        val petProfile = petProfileRepository.findByIdAndDeletedAtIsNull(petId) ?: throw ModelNotFoundException("pet", petId)

        if (petProfile.mainUser != mainUser) {
            throw Exception("해당 유저의 펫프로필이 아닙니다.")
        }
        petProfile.apply { name = request.name
            gender = request.gender
            birthDay = request.birthDay
            age = request.age
            kind = request.kind
            address = request.address
            aboutMe = request.aboutMe
            bloodType = request.bloodType
            weight = request.weight
            healthHistory = request.healthHistory
            profileImageUrl = request.profileImageUrl }

        return PetProfileResponseDTO.toResponse(petProfile)
    }

    @Transactional
    override fun deletePetProfile(userId: Long, petId: Long) {
        val mainUser = validateMainUser(userId)
        val petProfile = petProfileRepository.findByIdAndDeletedAtIsNull(petId) ?: throw ModelNotFoundException("pet", petId)
        if (petProfile.mainUser != mainUser) {
            throw Exception("해당 유저의 펫프로필이 아닙니다.")
        }
        petProfile.deletedAt = LocalDateTime.now()

    }
    private fun validateMainUser(userId: Long) = mainUserRepository.findByIdAndDeletedAtIsNull(userId) ?: throw ModelNotFoundException("user", userId)
}

