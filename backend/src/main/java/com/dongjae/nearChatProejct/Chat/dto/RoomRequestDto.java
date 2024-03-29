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
    private Double lat;
    private  Double lot;
    @Builder
    public RoomRequestDto(String name,Double lat, Double lot) {
        this.lat = lat;
        this.lot = lot;
        this.name = name;
    }
}
