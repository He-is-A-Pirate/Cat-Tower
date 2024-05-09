package heispirate.cattower.domain.authCode.repository

import heispirate.cattower.domain.authCode.model.AuthCode
import org.springframework.data.jpa.repository.JpaRepository

interface AuthCodeRepository : JpaRepository<AuthCode,Long> , CustomAuthCodeRepository {
}