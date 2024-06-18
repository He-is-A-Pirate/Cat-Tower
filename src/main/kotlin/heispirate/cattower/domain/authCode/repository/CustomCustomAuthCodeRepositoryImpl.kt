package heispirate.cattower.domain.authCode.repository

import heispirate.cattower.domain.authCode.model.QAuthCode
import heispirate.cattower.infra.QueryDslSupport
import java.time.LocalDateTime
import org.springframework.stereotype.Repository

@Repository
class CustomCustomAuthCodeRepositoryImpl : CustomAuthCodeRepository , QueryDslSupport() {
    private val authCode = QAuthCode.authCode
    override fun deleteByExpirationTime(zeroHour: LocalDateTime) {

        queryFactory.delete(authCode)
            .where(authCode.expirationTime.lt(zeroHour))
            .execute()

    }


}
