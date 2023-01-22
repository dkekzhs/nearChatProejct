package com.dongjae.nearChatProejct.User.Dto;

import lombok.Getter;

@Getter
public class UserSignInResponseDto {
    private String token;
    private int status;
    public UserSignInResponseDto(String token, int status) {
        this.token = token;
        this.status = status;
    }
}
