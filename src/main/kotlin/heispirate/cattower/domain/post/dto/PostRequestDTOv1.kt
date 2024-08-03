package heispirate.cattower.domain.post.dto

import heispirate.cattower.domain.petProfile.model.PetProfile
import heispirate.cattower.domain.post.model.Post
import heispirate.cattower.domain.post.model.SubCategory
import heispirate.cattower.infra.category.Category

data class PostRequestDTOv1(
    val title: String,
    val content: String,
    val category: Category,
    val subCategory: SubCategory,
) {
    fun toEntity(petProfile: PetProfile): Post {
        return Post(
            title = title,
            content = content,
            writer = petProfile.name,
            category = category,
            imageUrl = null,
            likeCounts = 0,
            petProfile = petProfile,
            subCategory = subCategory
        )
    }
}