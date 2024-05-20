package heispirate.cattower.domain.socialUser.model

import heispirate.cattower.infra.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Table(name = "socialUser")
@Entity
class SocialUser(
    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    val provider : OAuth2Provider,

    @Column(name = "providerId")
    val providerId : String,

    @Column(name = "nickname")
    val nickname : String,

    @Column(name = "email")
    val email : String,

): BaseEntity() {

}