package com.dongjae.nearChatProejct.User.Dto;


import com.dongjae.nearChatProejct.User.domain.UserEntity;
import com.dongjae.nearChatProejct.User.domain.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignUpRequestDto {

    private String deviceId;
    private String name;
    private UserRole role;

    public UserSignUpRequestDto(String deviceId, String name,UserRole role) {
        this.deviceId = deviceId;
        this.name = name;
        this.role = role;
    }

    public UserEntity ToEntity(String deviceId) {
        return UserEntity.builder().name(name)
                .deviceId(deviceId)
                .role(role)
                .build();
    }



}
