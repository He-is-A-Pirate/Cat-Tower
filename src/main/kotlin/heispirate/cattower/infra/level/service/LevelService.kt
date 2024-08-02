package heispirate.cattower.infra.level.service

import heispirate.cattower.infra.level.dto.LevelResponseDTO

interface LevelService {

    fun gainExp(exp: Int,userId: Long) : LevelResponseDTO

    fun loseExp(exp: Int,userId: Long) : LevelResponseDTO
}