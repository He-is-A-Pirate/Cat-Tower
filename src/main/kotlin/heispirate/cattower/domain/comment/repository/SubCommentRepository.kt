package heispirate.cattower.domain.comment.repository

import heispirate.cattower.domain.comment.model.SubComment
import org.springframework.data.jpa.repository.JpaRepository

interface SubCommentRepository : JpaRepository<SubComment,Long> , CustomSubCommentRepository {
}