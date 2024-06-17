package heispirate.cattower.domain.authCode.service

import heispirate.cattower.domain.authCode.dto.AuthResponseDTO

interface AuthCodeService {
    fun sendAuthEmail(email: String):AuthResponseDTO

    fun verifyCode(email: String, code: String): AuthResponseDTO
}