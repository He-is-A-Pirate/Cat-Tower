package heispirate.cattower.event

import heispirate.cattower.infra.level.service.LevelService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ExpChangeListener(private val levelService: LevelService) {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handleExpChangeEvent(event: ExpChangeEvent) {
        when (event.type) {
            ExpChangeType.GAIN -> levelService.gainExp(event.exp, event.userId)
            ExpChangeType.LOSE -> levelService.loseExp(event.exp, event.userId)
        }
    }
}