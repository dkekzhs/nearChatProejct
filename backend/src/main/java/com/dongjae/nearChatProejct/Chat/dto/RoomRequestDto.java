package com.dongjae.nearChatProejct.Chat.dto;

import com.dongjae.nearChatProejct.User.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomRequestDto {
    private String name;

    @Builder
    public RoomRequestDto(String name) {
        this.name = name;
    }
}
