package heispirate.cattower.domain.authCode.repository

import heispirate.cattower.domain.authCode.model.AuthCode
import java.time.LocalDateTime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface AuthCodeRepository : JpaRepository<AuthCode,Long> , CustomAuthCodeRepository {

    fun findByEmailAndAvailableIsTrue(email: String): AuthCode?

    @Modifying
    @Query("DELETE FROM AuthCode a WHERE a.expirationTime < :zeroHour")
    fun deleteByExpirationTime(zeroHour:LocalDateTime): Int


}