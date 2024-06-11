package heispirate.cattower.domain.mainUser.repository

import heispirate.cattower.domain.mainUser.model.MainUser
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository

interface MainUserRepository : JpaRepository<MainUser,Long> , CustomMainUserRepository {
    fun findByEmail(email: String): MainUser?
    fun existsByEmail(email: String):Boolean
    fun findByIdAndDeletedAtIsNull(userId: Long):MainUser?

}