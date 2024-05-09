package heispirate.cattower.domain.chat.repository

import heispirate.cattower.domain.chat.model.Chat
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRepository : JpaRepository<Chat,Long> , CustomChatRepository {
}