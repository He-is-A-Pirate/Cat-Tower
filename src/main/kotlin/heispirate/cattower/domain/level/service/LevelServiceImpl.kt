package heispirate.cattower.domain.level.service

import heispirate.cattower.domain.level.dto.ExperienceDTO
import heispirate.cattower.domain.level.dto.LevelResponseDTO
import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import heispirate.cattower.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LevelServiceImpl(private val mainUserRepository: MainUserRepository) : LevelService {
    override fun gainExp(exp: Int, userId: Long): LevelResponseDTO {

        val user = mainUserRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("mainUser", userId)

        val maxDailyExp = 200
        val gainedExp = if (user.todayExperience + exp > maxDailyExp) {
            maxDailyExp - user.todayExperience
        } else {
            exp
        }

        val updatedExperience = ExperienceDTO.fromMainUser(user).copy(
            todayExperience = user.todayExperience + gainedExp,
            totalExperience = user.experience + gainedExp
        )

        mainUserRepository.save(user.apply {
            todayExperience = updatedExperience.todayExperience
            experience = updatedExperience.totalExperience
        })

        levelUp(updatedExperience.totalExperience, userId)

        return LevelResponseDTO(updatedExperience.totalExperience, user.level)
    }

    override fun loseExp(exp: Int, userId: Long): LevelResponseDTO {
        TODO("Not yet implemented")
    }

    private fun levelUp(exp: Int, userId: Long) : LevelResponseDTO? {
        val user = mainUserRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("mainUser", userId)

        val maxLevel = 50
        val initialExp = 10

        val levelUpMap = mutableMapOf<Int,Int>()
        var expRequired = initialExp

        for (level in 0..maxLevel) {
            levelUpMap[level] = expRequired
            expRequired += expRequired / 2
        }

        val currentLevel = user.level
        var requiredExp = levelUpMap[currentLevel] ?: return null

        while (user.experience >= requiredExp && user.level < maxLevel) {
            requiredExp = levelUpMap[user.level + 1] ?: break
            user.level++
        }

        return if (user.level > currentLevel) {
            LevelResponseDTO.fromMainUser(user)
        } else {
            null
        }
    }

    private fun levelDown(exp: Int, userId: Long) : LevelResponseDTO? {
        TODO("Not yet implemented")
    }
}