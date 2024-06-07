package heispirate.cattower.domain.petProfile.service

import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import heispirate.cattower.domain.petProfile.dto.CreatePetProfileRequestDTO
import heispirate.cattower.domain.petProfile.dto.PetProfileResponseDTO
import heispirate.cattower.domain.petProfile.repository.PetProfileRepository
import heispirate.cattower.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PetProfileServiceImpl(
    private val mainUserRepository: MainUserRepository,
    private val petProfileRepository: PetProfileRepository
): PetProfileService {
    @Transactional
    override fun createPetProfile(
        userId: Long,
        createPetProfileRequestDTO: CreatePetProfileRequestDTO
    ): PetProfileResponseDTO {
        val mainUser = mainUserRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("user", userId)
        val petProfiles = petProfileRepository.findAllByMainUser(mainUser)
        if (petProfiles.isNotEmpty() && petProfiles.size >= 3) {
            throw Exception("펫 프로필 생성은 3개를 초과할 수 없습니다.")
        }
        val newPetProfile = createPetProfileRequestDTO.toEntity().apply { this.mainUser = mainUser }
        mainUser.petProfiles?.add(newPetProfile)
        val savedPetProfile = petProfileRepository.save(newPetProfile)
        return PetProfileResponseDTO.toResponse(savedPetProfile)
    }
    override fun getPetProfile(petId: Long): PetProfileResponseDTO {
        val petProfile = petProfileRepository.findByIdOrNull(petId) ?: throw ModelNotFoundException("pet", petId)
        return PetProfileResponseDTO.toResponse(petProfile)
    }
}