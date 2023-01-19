package com.dongjae.nearChatProejct.User.Dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignRequestDto {
    private String deviceId;
    private String name;

    public UserSignRequestDto(String deviceId, String name) {
        this.deviceId = deviceId;
        this.name = name;
    }
}
