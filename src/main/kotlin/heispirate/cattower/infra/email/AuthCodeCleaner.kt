package heispirate.cattower.infra.email

import heispirate.cattower.domain.authCode.repository.AuthCodeRepository
import heispirate.cattower.domain.authCode.service.AuthCodeService
import java.time.LocalDateTime
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class AuthCodeCleaner(
    private val authCodeService: AuthCodeService
) {
    @Scheduled(cron = "0 0 * * * *", zone = "Asia/Seoul")
    fun cleanAuthCode() {
        val zeroHour = LocalDateTime.now()
        authCodeService.deleteAuthCode(zeroHour)
        //현재시간 보다 ? 작으면 다 지운다  -> 11시59분에 인증중일수도있음 그럼 시간 잘 분배하면 되겠는데?
        // mbti가 adhd 인 사람이 인증코드를 1회 전송하고 그냥 쌩까버리면 사용가능여부가 true로 남게된다
        // 그냥 유기해놓고 유효시간이 현재시간보다 작다면 그냥 다 날려?
        //현재시간 > 유효시간 인 친구들 싸그리 날려
    }

}