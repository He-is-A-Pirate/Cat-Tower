package heispirate.cattower.domain.post.service

import heispirate.cattower.domain.mainUser.repository.MainUserRepository
import heispirate.cattower.domain.petProfile.repository.PetProfileRepository
import heispirate.cattower.domain.post.dto.PostRequestDTOv1
import heispirate.cattower.domain.post.dto.PostResponseDTOv1
import heispirate.cattower.domain.post.repository.PostRepository
import heispirate.cattower.event.ExpChangeEvent
import heispirate.cattower.event.ExpChangeType
import heispirate.cattower.exception.ModelNotFoundException
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val postRepository: PostRepository,
    private val petProfileRepository: PetProfileRepository,
    private val mainUserRepository: MainUserRepository,
) : PostService {

    @Transactional
    override fun createPostV1(request: PostRequestDTOv1, userId: Long,petProfileId:Long): PostResponseDTOv1 {
        val mainUser = mainUserRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("user",userId)
        val petProfile = petProfileRepository.findByIdOrNull(petProfileId) ?: throw ModelNotFoundException("pet",petProfileId)

        if (petProfile.mainUser.id != mainUser.id) {
            throw IllegalArgumentException("유저와 펫의 주인이 다릅니다")
        }

        val postEntity = request.toEntity(petProfile)
        val savePost = postRepository.save(postEntity)

        val expChangeEvent = ExpChangeEvent(
            userId = mainUser.id ?: throw IllegalStateException("userId가 null 입니다"),
            exp = 199,  // 경험치 양은 예시 입니다.
            type = ExpChangeType.GAIN
        )
        applicationEventPublisher.publishEvent(expChangeEvent)

        return PostResponseDTOv1.fromPost(savePost)
    }

    @Transactional
    override fun deletePostV1(postId: Long, userId: Long): Boolean {
        val mainUser = mainUserRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("user", userId)
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("post", postId)

        if (post.petProfile.mainUser.id != mainUser.id) {
            throw IllegalArgumentException("사용자와 게시글 작성자의 소유주가 다릅니다")
        }

        postRepository.delete(post)

        val expChangeEvent = ExpChangeEvent(
            userId = mainUser.id ?: throw IllegalStateException("userId가 null 입니다"),
            exp = 180,  // 경험치 양은 예시입니다.
            type = ExpChangeType.LOSE
        )
        applicationEventPublisher.publishEvent(expChangeEvent)

        return true
    }

    @Transactional
    override fun getPostV1(postId: Long): PostResponseDTOv1 {
        val post = postRepository.findById(postId).orElseThrow { ModelNotFoundException("post", postId) }
        return PostResponseDTOv1.fromPost(post)
    }

    @Transactional
    override fun getAllPostsV1(pageable: Pageable): Page<PostResponseDTOv1> {
        val posts = postRepository.findAll(pageable)
        return posts.map { post -> PostResponseDTOv1.fromPost(post) }
    }

    @Transactional
    override fun getPostsByPetProfileV1(petProfileId: Long, pageable: Pageable): Page<PostResponseDTOv1> {
        val posts = postRepository.findByPetProfileId(petProfileId, pageable) ?: throw ModelNotFoundException("post",petProfileId)
        return posts.map { post -> PostResponseDTOv1.fromPost(post) }
    }
}