package heispirate.cattower.domain.petProfile.service

import heispirate.cattower.domain.petProfile.dto.PetProfileRequestDTO
import heispirate.cattower.domain.petProfile.dto.PetProfileResponseDTO

interface PetProfileService {
    fun createPetProfile(userId: Long, request: PetProfileRequestDTO
    ): PetProfileResponseDTO

    fun getPetProfile(userId: Long, petId: Long): PetProfileResponseDTO

    fun getUsersPetProfileList(userId: Long): List<PetProfileResponseDTO>

    fun updatePetProfile(userId: Long, petId: Long, request: PetProfileRequestDTO): PetProfileResponseDTO

    fun deletePetProfile(userId: Long, petId: Long)

}