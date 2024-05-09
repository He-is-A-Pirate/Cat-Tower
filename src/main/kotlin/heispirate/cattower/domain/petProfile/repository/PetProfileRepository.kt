package heispirate.cattower.domain.petProfile.repository

import heispirate.cattower.domain.petProfile.model.PetProfile
import org.springframework.data.jpa.repository.JpaRepository

interface PetProfileRepository : JpaRepository<PetProfile,Long> , CustomPetProfileRepository {
}