package heispirate.cattower.domain.level.service

import heispirate.cattower.domain.level.dto.ExperienceDTO
import heispirate.cattower.domain.level.dto.LevelResponseDTO
import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import heispirate.cattower.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LevelServiceImpl(private val mainUserRepository: MainUserRepository) : LevelService {
    override fun gainExp(exp: Int, userId: Long): LevelResponseDTO {

        val user = getUserById(userId)

        val maxDailyExp = 200
        val gainedExp = if (user.todayExperience + exp > maxDailyExp) {
            maxDailyExp - user.todayExperience
        } else {
            exp
        }

        val updatedExp = ExperienceDTO.fromMainUser(user).copy(
            todayExperience = user.todayExperience + gainedExp, totalExperience = user.experience + gainedExp
        )

        mainUserRepository.save(user.apply {
            todayExperience = updatedExp.todayExperience
            experience = updatedExp.totalExperience
        })

        changeLevel(user)

        return LevelResponseDTO(updatedExp.totalExperience, user.level)
    }

    override fun loseExp(exp: Int, userId: Long): LevelResponseDTO {
        val user = getUserById(userId)

        val lostExp = if (user.experience < exp) {
            user.experience
        } else {
            exp
        }

        val updatedExp = ExperienceDTO.fromMainUser(user).copy(
            todayExperience = if (user.todayExperience < lostExp) 0 else user.todayExperience - lostExp,
            totalExperience = user.experience - lostExp
        )

        mainUserRepository.save(user.apply {
            todayExperience = updatedExp.todayExperience
            experience = updatedExp.totalExperience
        })

        changeLevel(user)

        return LevelResponseDTO(updatedExp.totalExperience, user.level)
    }

    private fun getUserById(userId: Long) : MainUser {
        return mainUserRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("mainUser", userId)
    }

    private fun changeLevel(user: MainUser): LevelResponseDTO? {
        val maxLevel = 50
        val initialExp = 10

        val levelMap = mutableMapOf<Int, Int>()
        var expRequired = initialExp

        for (level in 1..maxLevel) {
            levelMap[level] = expRequired
            expRequired += expRequired / 2
        }

        val currentLevel = user.level
        var newLevel = currentLevel
        var requiredExp = levelMap[newLevel] ?: return null

        // 레벨 업 로직
        while (user.experience >= requiredExp && newLevel < maxLevel) {
            newLevel++
            requiredExp = levelMap[newLevel] ?: break
        }

        // 레벨 다운 로직
        while (user.experience < requiredExp && newLevel > 1) {
            newLevel--
            requiredExp = levelMap[newLevel] ?: 0
        }


        if (newLevel != currentLevel) {
            user.level = newLevel
            mainUserRepository.save(user)
            return LevelResponseDTO.fromMainUser(user)
        }

        return null
    }
}