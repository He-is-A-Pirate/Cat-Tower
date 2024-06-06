package heispirate.cattower.domain.mainUser.service

import heispirate.cattower.domain.mainUser.dto.MainUserRequestDTO
import heispirate.cattower.domain.mainUser.dto.MainUserResponseDTO
import heispirate.cattower.domain.mainUser.dto.SingUpRequestDTO

interface MainUserService {
    fun signUp(
        signUpRequestDTO: SingUpRequestDTO
    )

    fun getUser(userId: Long): MainUserResponseDTO

    fun updateUser(userId: Long, mainUserRequestDTO: MainUserRequestDTO): MainUserResponseDTO

    fun deleteUser(userId: Long)
}