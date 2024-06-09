package heispirate.cattower.domain.petProfile.service

import heispirate.cattower.domain.petProfile.dto.PetProfileRequestDTO
import heispirate.cattower.domain.petProfile.dto.PetProfileResponseDTO

interface PetProfileService {
    fun createPetProfile(userId: Long, petProfileRequestDTO: PetProfileRequestDTO
    ): PetProfileResponseDTO

    fun getPetProfile(userId: Long, petId: Long): PetProfileResponseDTO


}