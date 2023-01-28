package com.dongjae.nearChatProejct.Chat.service;

import com.dongjae.nearChatProejct.Chat.domain.ChatRoom;
import com.dongjae.nearChatProejct.Chat.domain.ChatRoomRepository;
import com.dongjae.nearChatProejct.Chat.domain.MessageRepository;
import com.dongjae.nearChatProejct.User.domain.UserEntity;
import com.dongjae.nearChatProejct.User.exception.UserNotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom findByChatId(Long id) {
        return this.chatRoomRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public void createRoom(ChatRoom chatRoom) {
        this.chatRoomRepository.save(chatRoom);
    }
}
