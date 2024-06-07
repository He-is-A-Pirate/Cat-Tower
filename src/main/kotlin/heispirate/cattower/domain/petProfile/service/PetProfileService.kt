package heispirate.cattower.domain.petProfile.service

import heispirate.cattower.domain.petProfile.dto.CreatePetProfileRequestDTO
import heispirate.cattower.domain.petProfile.dto.PetProfileResponseDTO

interface PetProfileService {
    fun createPetProfile(userId: Long, createPetProfileRequestDTO: CreatePetProfileRequestDTO
    ): PetProfileResponseDTO
}