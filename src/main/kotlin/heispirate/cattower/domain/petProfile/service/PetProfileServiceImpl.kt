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
        petProfileRequestDTO: PetProfileRequestDTO
    ): PetProfileResponseDTO {
        val mainUser = mainUserRepository.findByIdAndDeletedAtIsNull(userId) ?: throw ModelNotFoundException("user", userId)
        val petProfiles = petProfileRepository.findAllByMainUserAndDeletedAtIsNull(mainUser)
        if (petProfiles.size >= 3) {
            throw Exception("펫 프로필 생성은 3개를 초과할 수 없습니다.")
        }
        val newPetProfile = petProfileRequestDTO.toEntity(mainUser) // .apply { this.mainUser = mainUser }
        val savedPetProfile = petProfileRepository.save(newPetProfile)
        // mainUser.petProfiles?.add(newPetProfile)
        return PetProfileResponseDTO.toResponse(savedPetProfile)
    }
    override fun getPetProfile(
        userId: Long,
        petId: Long
    ): PetProfileResponseDTO {
        val petProfile =
            petProfileRepository.findByIdAndDeletedAtIsNull(petId) ?: throw ModelNotFoundException("pet", petId)
        val mainUser = mainUserRepository.findByIdAndDeletedAtIsNull(userId) ?: throw ModelNotFoundException("user", userId)
        if (!petProfile.disclosure && petProfile.mainUser?.id != mainUser.id) {
                throw Exception("비공개 프로필입니다.")
            }
        return PetProfileResponseDTO.toResponse(petProfile)
    }

    override fun getUsersPetProfileList(userId: Long): List<PetProfileResponseDTO> {
        val mainUser = mainUserRepository.findByIdAndDeletedAtIsNull(userId) ?: throw ModelNotFoundException("user", userId)
        val petProfiles = petProfileRepository.findAllByMainUserAndDeletedAtIsNull(mainUser).filter{it.disclosure}
        return petProfiles.map { PetProfileResponseDTO.toResponse(it) }
    }

    @Transactional
    override fun updatePetProfile(userId: Long, petId: Long, petProfileRequestDTO: PetProfileRequestDTO
    ): PetProfileResponseDTO {
        val mainUser = mainUserRepository.findByIdAndDeletedAtIsNull(userId) ?: throw ModelNotFoundException("user", userId)
        val petProfile = petProfileRepository.findByIdAndDeletedAtIsNull(petId) ?: throw ModelNotFoundException("pet", petId)

        if (petProfile.mainUser != mainUser) {
            throw Exception("해당 유저의 펫프로필이 아닙니다.")
        }
        petProfile.apply { name = petProfileRequestDTO.name
            gender = petProfileRequestDTO.gender
            birthDay = petProfileRequestDTO.birthDay
            age = petProfileRequestDTO.age
            kind = petProfileRequestDTO.kind
            address = petProfileRequestDTO.address
            aboutMe = petProfileRequestDTO.aboutMe
            bloodType = petProfileRequestDTO.bloodType
            weight = petProfileRequestDTO.weight
            healthHistory = petProfileRequestDTO.healthHistory
            profileImageUrl = petProfileRequestDTO.profileImageUrl
            disclosure = petProfileRequestDTO.disclosure}

        return PetProfileResponseDTO.toResponse(petProfile)
    }

    @Transactional
    override fun deletePetProfile(userId: Long, petId: Long) {
        val mainUser = mainUserRepository.findByIdAndDeletedAtIsNull(userId) ?: throw ModelNotFoundException("user", userId)
        val petProfile = petProfileRepository.findByIdAndDeletedAtIsNull(petId) ?: throw ModelNotFoundException("pet", petId)
        if (petProfile.mainUser != mainUser) {
            throw Exception("해당 유저의 펫프로필이 아닙니다.")
        }
        petProfile.deletedAt = LocalDateTime.now()

    }
}