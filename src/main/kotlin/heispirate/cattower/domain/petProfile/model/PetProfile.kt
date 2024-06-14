package heispirate.cattower.domain.petProfile.model

import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.infra.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "petProfile")
@Entity
class PetProfile(
    @Column(name = "name")
    var name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    var gender: Gender,

    @Column(name = "birthDay")
    var birthDay: LocalDateTime,

    @Column(name = "age")
    var age: Int,

    @Column(name = "kind")
    var kind: String?,

    @Column(name = "address")
    var address: String?,

    @Column(name = "aboutMe")
    var aboutMe: String?,

    @Column(name = "bloodType")
    var bloodType: String?,

    @Column(name = "weight")
    var weight: Double?,

    @Column(name = "healthHistory")
    var healthHistory: String?,

    @Column(name = "profileImageUrl")
    var profileImageUrl: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_user_id")
    var mainUser: MainUser

    ) : BaseEntity() {

}