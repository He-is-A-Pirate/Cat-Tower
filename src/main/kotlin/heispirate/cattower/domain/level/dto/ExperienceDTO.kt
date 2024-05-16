package heispirate.cattower.domain.level.dto

import heispirate.cattower.domain.mainUser.model.MainUser

data class ExperienceDTO(
    val todayExperience: Int,
    val totalExperience: Int
) {
    companion object {
        fun fromMainUser(user: MainUser): ExperienceDTO {
            return ExperienceDTO(
                todayExperience = user.todayExperience,
                totalExperience = user.experience
            )
        }
    }
}
