package heispirate.cattower.domain.chat.model

import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.infra.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "chat")
@Entity
class Chat(
    @Column(name = "content")
    val content : String,

    @Column(name = "date")
    val date : LocalDateTime,

    @Column(name = "imageUrl")
    val imageUrl : String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mainUserId")
    val mainUser: MainUser,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatRoomId")
    val chatRoom: ChatRoom,

) : BaseEntity() {
}