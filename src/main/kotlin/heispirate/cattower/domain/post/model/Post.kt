package heispirate.cattower.domain.post.model

import heispirate.cattower.domain.petProfile.model.PetProfile
import heispirate.cattower.infra.BaseTimeEntity
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
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

    @Column(name = "writer")
    val writer : String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petProfileId")
    val petProfile : PetProfile

) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}