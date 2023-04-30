package com.dongjae.nearChatProejct.User.Dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateXYRequestDto {
    private Double lat;
    private Double lot;

    public UpdateXYRequestDto(Double lat, Double lot) {
        this.lat = lat;
        this.lot = lot;
    }
}
