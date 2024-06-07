package heispirate.cattower.domain.mainUser.model

import heispirate.cattower.domain.petProfile.model.PetProfile
import heispirate.cattower.infra.BaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Table(name = "mainUser")
@Entity
class MainUser(
    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    var password: String?,

    @Column(name = "aboutMe")
    var aboutMe: String?,

    @Column(name = "phoneNumber")
    var phoneNumber: String,

    @Column(name = "todayExperience")
    var todayExperience: Int = 0,

    @Column(name = "experience")
    var experience: Int = 0,

    @Column(name = "level")
    var level: Int = 1,

    @Column(name = "provider")
    val provider: String?,

    @Column(name = "providerId")
    val providerId: String?,

    @OneToMany(mappedBy = "mainUser", cascade = [CascadeType.ALL], orphanRemoval=true, fetch = FetchType.LAZY)
    var petProfiles: MutableList<PetProfile>? = mutableListOf()

    ) : BaseEntity() {

}