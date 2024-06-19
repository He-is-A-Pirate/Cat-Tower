package heispirate.cattower.domain.authCode.repository

import heispirate.cattower.infra.QueryDslSupport
import org.springframework.stereotype.Repository


class CustomAuthCodeRepositoryImpl : CustomAuthCodeRepository , QueryDslSupport() {

}
