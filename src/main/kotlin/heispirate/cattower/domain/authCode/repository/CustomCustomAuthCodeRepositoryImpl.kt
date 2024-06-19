package heispirate.cattower.domain.authCode.repository

import heispirate.cattower.infra.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository
class CustomCustomAuthCodeRepositoryImpl : CustomAuthCodeRepository , QueryDslSupport() {

}
