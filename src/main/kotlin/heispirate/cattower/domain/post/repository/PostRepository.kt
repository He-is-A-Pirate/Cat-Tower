package heispirate.cattower.domain.post.repository

import heispirate.cattower.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post,Long> , CustomPostRepository{
}