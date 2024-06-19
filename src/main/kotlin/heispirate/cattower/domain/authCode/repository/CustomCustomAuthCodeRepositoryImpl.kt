package heispirate.cattower.domain.authCode.repository

import heispirate.cattower.domain.authCode.model.QAuthCode
import heispirate.cattower.infra.QueryDslSupport
import jakarta.transaction.Transactional
import java.time.LocalDateTime
import org.springframework.stereotype.Repository

@Repository
class CustomCustomAuthCodeRepositoryImpl : CustomAuthCodeRepository , QueryDslSupport() {

}
