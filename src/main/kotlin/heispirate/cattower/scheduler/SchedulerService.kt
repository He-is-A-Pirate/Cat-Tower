package heispirate.cattower.scheduler

import heispirate.cattower.domain.authCode.service.AuthCodeService
import java.time.LocalDateTime
import java.time.ZoneId
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SchedulerService(
    private val authCodeService: AuthCodeService,
){
    @Scheduled(cron = "0 0 0 * * *")
    fun cleanAuthCode() {
        val zeroHour = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
            authCodeService.deleteAuthCode(zeroHour)
    }

}