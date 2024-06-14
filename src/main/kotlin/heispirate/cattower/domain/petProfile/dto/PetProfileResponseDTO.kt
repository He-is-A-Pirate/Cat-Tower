package heispirate.cattower.domain.petProfile.dto

import heispirate.cattower.domain.petProfile.model.Gender
import heispirate.cattower.domain.petProfile.model.PetProfile
import java.time.LocalDateTime
data class PetProfileResponseDTO(
    val name: String,
    val gender: Gender,
    val birthday: LocalDateTime,
    val age: Int,
    val kind: String?,
    val address: String?,
    val aboutMe: String?,
    val bloodType: String?,
    val weight: Double?,
    val healthHistory: String?,
    val profileImageUrl: String?,
) {

    companion object {
        fun toResponse(petProfile: PetProfile): PetProfileResponseDTO {
            return PetProfileResponseDTO(
                name = petProfile.name,
                gender = petProfile.gender,
                birthday = petProfile.birthDay,
                age = petProfile.age,
                kind = petProfile.kind,
                address = petProfile.address,
                aboutMe = petProfile.aboutMe,
                bloodType = petProfile.bloodType,
                weight = petProfile.weight,
                healthHistory = petProfile.healthHistory,
                profileImageUrl = petProfile.profileImageUrl,
            )
        }
    }
}
