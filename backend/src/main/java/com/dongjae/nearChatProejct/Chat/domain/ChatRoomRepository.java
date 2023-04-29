package com.dongjae.nearChatProejct.Chat.domain;

import com.dongjae.nearChatProejct.User.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
}
