package heispirate.cattower.domain.post.dto

import heispirate.cattower.domain.post.model.Post
import heispirate.cattower.domain.post.model.SubCategory
import heispirate.cattower.infra.category.Category

data class PostResponseDTOv1(
    val title: String,
    val content: String,
    val writer: String,
    val category: Category,
    val subCategory: SubCategory
) {
    companion object {
        fun fromPost(post: Post): PostResponseDTOv1 {
            return PostResponseDTOv1(
                title = post.title,
                content = post.content,
                writer = post.writer,
                category = post.category,
                subCategory = post.subCategory
            )
        }
    }
}