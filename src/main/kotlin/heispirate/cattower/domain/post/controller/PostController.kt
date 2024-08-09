package heispirate.cattower.domain.post.controller

import heispirate.cattower.domain.post.dto.PostRequestDTOv1
import heispirate.cattower.domain.post.dto.PostResponseDTOv1
import heispirate.cattower.domain.post.service.PostService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService
) {

    @Operation(summary = "글 생성", description = "사용자가 새로운 글을 생성 합니다.")
    @PostMapping("/{userId}/petProfile/{petProfileId}")
    fun createPostV1(
        @PathVariable userId: Long,
        @PathVariable petProfileId: Long,
        @RequestBody request: PostRequestDTOv1
    ): ResponseEntity<PostResponseDTOv1> {
        val postResponse = postService.createPostV1(request, userId, petProfileId)
        return ResponseEntity.ok(postResponse)
    }

    @Operation(summary = "글 삭제", description = "사용자가 자신의 글을 삭제 합니다.")
    @DeleteMapping("/{userId}/{postId}")
    fun deletePostV1(
        @PathVariable postId: Long,
        @PathVariable userId: Long,
    ): ResponseEntity<Boolean> {
        val result = postService.deletePostV1(postId, userId)
        return ResponseEntity.ok(result)
    }

    @Operation(summary = "단일 글 조회", description = "단일 글을 조회 합니다.")
    @GetMapping("/{postId}")
    fun getPostV1(
        @PathVariable postId: Long
    ): ResponseEntity<PostResponseDTOv1> {
        val postResponse = postService.getPostV1(postId)
        return ResponseEntity.ok(postResponse)
    }

    @Operation(summary = "전체 글 페이징 조회", description = "전체 글을 페이징 조회 합니다.")
    @GetMapping
    fun getAllPostsV1(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "5") size: Int
    ): ResponseEntity<Page<PostResponseDTOv1>> {
        val pageable: Pageable = PageRequest.of(page, size)
        val postPage = postService.getAllPostsV1(pageable)
        return ResponseEntity.ok(postPage)
    }

    @Operation(summary = "펫 프로필 기준 글 페이징 조회", description = "펫 프로필로 작성한 글 전체를 조회 합니다.")
    @GetMapping("/pet/{petProfileId}")
    fun getPostsByPetProfileV1(
        @PathVariable petProfileId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "5") size: Int
    ): ResponseEntity<Page<PostResponseDTOv1>> {
        val pageable: Pageable = PageRequest.of(page, size)
        val postPage = postService.getPostsByPetProfileV1(petProfileId, pageable)
        return ResponseEntity.ok(postPage)
    }

}