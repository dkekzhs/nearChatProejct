package com.dongjae.nearChatProejct.User.Dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateXYRadiusRequestDto {
    private Double lat;
    private Double lot;
    private int radius;

    public CreateXYRadiusRequestDto(Double lat, Double lot, int radius) {
        this.lat = lat;
        this.lot = lot;
        this.radius = radius;
    }
}
