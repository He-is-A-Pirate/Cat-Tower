package heispirate.cattower.domain.likes.model

import heispirate.cattower.domain.comment.model.Comment
import heispirate.cattower.domain.comment.model.SubComment
import heispirate.cattower.domain.petProfile.model.PetProfile
import heispirate.cattower.domain.post.model.Post
import heispirate.cattower.infra.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Table(name = "likes")
@Entity
class Likes(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    val post: Post?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petProfileId")
    val petProfile: PetProfile,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentId")
    val comment: Comment?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SubCommentId")
    val subComment: SubComment?

) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}