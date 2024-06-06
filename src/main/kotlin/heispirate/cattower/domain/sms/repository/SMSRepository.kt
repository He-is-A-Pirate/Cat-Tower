package heispirate.cattower.domain.sms.repository

import heispirate.cattower.domain.sms.model.SMS
import org.springframework.data.jpa.repository.JpaRepository

interface SMSRepository : JpaRepository<SMS,Long> {
}