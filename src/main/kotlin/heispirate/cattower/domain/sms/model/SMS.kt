package heispirate.cattower.domain.sms.model

import heispirate.cattower.infra.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Table(name = "sms")
@Entity
class SMS(
    @Column(name = "text")
    var text: String,

    @Column(name = "phoneNumber")
    var phoneNumber: String,
) : BaseEntity() {
}