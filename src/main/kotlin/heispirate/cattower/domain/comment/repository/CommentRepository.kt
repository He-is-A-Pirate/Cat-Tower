package heispirate.cattower.domain.comment.repository

import heispirate.cattower.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment,Long> , CustomCommentRepository {
}