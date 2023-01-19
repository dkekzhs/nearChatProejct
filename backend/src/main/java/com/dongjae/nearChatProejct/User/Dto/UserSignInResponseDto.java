package com.dongjae.nearChatProejct.User.Dto;

import lombok.Getter;

@Getter
public class UserSignInResponseDto {
    private String token;

    public UserSignInResponseDto(String token) {
        this.token = token;
    }
}
