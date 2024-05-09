package heispirate.cattower.domain.authCode.model

import heispirate.cattower.infra.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "authCode")
@Entity
class AuthCode(
    @Column(name = "code")
    val code : String,

    @Column(name = "expirationTime")
    val expirationTime : LocalDateTime,

    @Column(name = "email")
    val email : String,

    @Column(name = "requestNumber")
    var requestNumber : Int,

):BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}