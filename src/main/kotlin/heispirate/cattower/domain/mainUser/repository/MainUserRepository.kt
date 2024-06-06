package heispirate.cattower.domain.mainUser.repository

import heispirate.cattower.domain.mainUser.model.MainUser
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository

interface MainUserRepository : JpaRepository<MainUser,Long> , CustomMainUserRepository {
    fun existsByEmail(email: String):Boolean

}