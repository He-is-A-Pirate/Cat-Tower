package heispirate.cattower.domain.admin.model

import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.infra.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Table(name = "admin")
@Entity
class Admin(

    @Column(name = "role")
    var role : String,

    @OneToOne(fetch = FetchType.LAZY)
    val mainUser: MainUser,

):BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}