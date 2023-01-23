package com.dongjae.nearChatProejct.User.Dto;

import com.dongjae.nearChatProejct.User.domain.UserEntity;
import com.dongjae.nearChatProejct.User.domain.UserRole;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String deviceId;
    private String name;
    private UserRole role;
    private int status;


    public UserResponseDto(UserEntity user, int status) {
        this.deviceId = user.getDeviceId();
        this.name = user.getName();
        this.role = user.getRole();
        this.status = status;
    }
}
