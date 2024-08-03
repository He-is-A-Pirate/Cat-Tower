package heispirate.cattower.domain.post.service

import heispirate.cattower.domain.post.dto.PostRequestDTOv1
import heispirate.cattower.domain.post.dto.PostResponseDTOv1
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostService {

    fun createPostV1(request : PostRequestDTOv1,userId : Long, petProfileId :Long) : PostResponseDTOv1

    fun deletePostV1(postId: Long, userId: Long): Boolean

    fun getPostV1(postId: Long): PostResponseDTOv1

    fun getAllPostsV1(pageable: Pageable): Page<PostResponseDTOv1>

    fun getPostsByPetProfileV1(petProfileId: Long, pageable: Pageable): Page<PostResponseDTOv1>
}