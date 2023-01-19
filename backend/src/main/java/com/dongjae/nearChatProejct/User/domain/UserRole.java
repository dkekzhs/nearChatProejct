package com.dongjae.nearChatProejct.User.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
public enum UserRole {
    USER("일반 권한 유저"),
    STAFF("수정 권한 부여 유저"),
    AMDIN("모든 관리 권한 유저");
    private final String role;
}
