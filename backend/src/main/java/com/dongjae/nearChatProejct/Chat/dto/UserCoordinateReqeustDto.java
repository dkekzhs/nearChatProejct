package com.dongjae.nearChatProejct.Chat.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCoordinateReqeustDto {
    private Double lat;
    private Double lot;
    private int radius;

    @Builder

    public UserCoordinateReqeustDto(Double lat, Double lot, int radius) {
        this.lat = lat;
        this.lot = lot;
        this.radius = radius;
    }
}
