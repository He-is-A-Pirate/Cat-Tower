package heispirate.cattower.domain.comment.model

import heispirate.cattower.domain.petProfile.model.PetProfile
import heispirate.cattower.domain.post.model.Post
import heispirate.cattower.infra.BaseEntity
import heispirate.cattower.infra.category.Category
import heispirate.cattower.infra.category.CategoryInterface
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Table(name = "comment")
@Entity
class Comment(
    @Column(name = "content")
    var content : String,

    @Column(name = "imageUrl")
    var imageUrl : String?,

    @Column(name = "likeCounts")
    var likeCounts : Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    val post : Post,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petProfileId")
    val petProfile: PetProfile,

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    override var category: Category

) : BaseEntity(), CategoryInterface {

}
