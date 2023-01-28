package com.dongjae.nearChatProejct.Chat.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomResponseDto {
    private String name;
    private int status;

    @Builder
    public RoomResponseDto(String name, int status) {
        this.name = name;
        this.status = status;
    }
}
