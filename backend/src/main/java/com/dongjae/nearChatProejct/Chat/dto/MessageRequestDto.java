package com.dongjae.nearChatProejct.Chat.dto;

import com.dongjae.nearChatProejct.Chat.domain.ChatRoom;
import com.dongjae.nearChatProejct.User.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRequestDto {
    private UserEntity receiverId;
    private UserEntity memberId;
    private ChatRoom chatRoom;

    public MessageRequestDto(UserEntity receiverId, UserEntity memberId, ChatRoom chatRoom) {
        this.receiverId = receiverId;
        this.memberId = memberId;
        this.chatRoom = chatRoom;
    }
}
