package heispirate.cattower.domain.post.repository

import heispirate.cattower.domain.post.model.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post,Long> , CustomPostRepository{

    fun findByPetProfileId(petProfileId: Long, pageable: Pageable): Page<Post>?
}