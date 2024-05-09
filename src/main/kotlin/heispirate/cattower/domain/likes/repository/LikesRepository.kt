package heispirate.cattower.domain.likes.repository

import heispirate.cattower.domain.likes.model.Likes
import org.springframework.data.jpa.repository.JpaRepository

interface LikesRepository : JpaRepository<Likes,Long> , CustomLikesRepository {
}