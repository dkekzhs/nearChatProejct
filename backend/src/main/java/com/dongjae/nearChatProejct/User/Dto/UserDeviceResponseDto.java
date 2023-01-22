package com.dongjae.nearChatProejct.User.Dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDeviceResponseDto {
    private String deviceId;

    public UserDeviceResponseDto(String deviceId) {
        this.deviceId = deviceId;
    }
}
