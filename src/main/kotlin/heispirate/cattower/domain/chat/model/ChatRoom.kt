package heispirate.cattower.domain.chat.model

import heispirate.cattower.domain.mainUser.model.MainUser
import heispirate.cattower.domain.post.model.Post
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.*

@Table(name = "chatRooms")
@Entity
class ChatRoom(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caller", updatable = false)
    val caller: MainUser,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver", updatable = false)
    val receiver: MainUser,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post", updatable = false)
    val post: Post,

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    var id: Long? = null

}