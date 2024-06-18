package heispirate.cattower.domain.authCode.repository

import java.time.LocalDateTime

interface CustomAuthCodeRepository {
    fun deleteByExpirationTime(zeroHour: LocalDateTime)
}