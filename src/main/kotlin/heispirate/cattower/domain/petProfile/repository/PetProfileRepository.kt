package heispirate.cattower.domain.petProfile.repository

import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.domain.petProfile.model.PetProfile
import org.springframework.data.jpa.repository.JpaRepository

interface PetProfileRepository : JpaRepository<PetProfile,Long> , CustomPetProfileRepository {

    fun findAllByMainUser(mainUser: MainUser): List<PetProfile>

    fun findByIdAndDeletedAtIsNull(petId: Long): PetProfile?
}