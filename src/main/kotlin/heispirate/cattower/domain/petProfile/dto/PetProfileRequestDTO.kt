package heispirate.cattower.domain.petProfile.dto

import heispirate.cattower.domain.petProfile.model.Gender
import heispirate.cattower.domain.petProfile.model.PetProfile
import java.time.LocalDateTime

data class PetProfileRequestDTO(
    val name: String,
    val gender: Gender,
    val birthDay: LocalDateTime,
    val age: Int,
    val kind: String?,
    val address: String?,
    val aboutMe: String?,
    val bloodType: String?,
    val weight: Double?,
    val healthHistory: String?,
    val profileImageUrl: String,
    val disclosure: Boolean,
) {
    fun toEntity(): PetProfile {
            return PetProfile(
                name = name,
                gender = gender,
                birthDay = birthDay,
                age = age,
                kind = kind,
                address = address,
                aboutMe = aboutMe,
                bloodType = bloodType,
                weight = weight,
                healthHistory = healthHistory,
                profileImageUrl = profileImageUrl,
                disclosure = disclosure,
            )
    }
}
