package heispirate.cattower.domain.level.service

import heispirate.cattower.domain.level.dto.LevelResponseDTO

interface LevelService {

    fun gainExp(exp: Int,userId: Long) : LevelResponseDTO

    fun loseExp(exp: Int,userId: Long) : LevelResponseDTO
}