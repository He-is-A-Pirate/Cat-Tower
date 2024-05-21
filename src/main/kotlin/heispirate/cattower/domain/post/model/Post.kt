package heispirate.cattower.domain.post.model

import heispirate.cattower.domain.petProfile.model.PetProfile
import heispirate.cattower.infra.BaseEntity
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Table(name = "posts")
@Entity
class Post(
    @Column(name = "title")
    var title : String,

    @Column(name = "content")
    var content : String,

    @ElementCollection
    @CollectionTable(name = "imageUrls")
    @Column(name = "imageUrl")
    var imageUrl : List<String>?,

    @Column(name = "likeCounts")
    var likeCounts : Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    var category : Category,

    @Enumerated(EnumType.STRING)
    @Column(name = "subCategory")
    var subCategory: SubCategory,

    @Column(name = "writer")
    val writer : String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petProfileId")
    val petProfile : PetProfile

) : BaseEntity() {

}