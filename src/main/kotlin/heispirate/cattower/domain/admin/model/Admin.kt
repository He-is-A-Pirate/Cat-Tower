package heispirate.cattower.domain.admin.model

import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.infra.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Table(name = "admin")
@Entity
class Admin(

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    var role: Role,

    @OneToOne(fetch = FetchType.LAZY)
    val mainUser: MainUser,

    ):BaseEntity() {

}