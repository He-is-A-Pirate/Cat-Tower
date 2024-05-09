package heispirate.cattower.domain.mainUser.model

import heispirate.cattower.domain.chat.model.Chat
import heispirate.cattower.infra.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
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

    @Column(name = "experience")
    var experience: Int = 0,

    @Column(name = "level")
    var level: Int = 1,

    @Column(name = "provider")
    val provider: String?,

    @Column(name = "providerId")
    val providerId: String?,

    ) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}