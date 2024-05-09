package heispirate.cattower.domain.socialUser.repository

import heispirate.cattower.domain.socialUser.model.SocialUser
import org.springframework.data.jpa.repository.JpaRepository

interface SocialUserRepository : JpaRepository<SocialUser,Long> , CustomSocialUserRepository {
}