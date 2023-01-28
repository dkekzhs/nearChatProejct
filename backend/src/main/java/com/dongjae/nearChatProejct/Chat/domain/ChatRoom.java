package com.dongjae.nearChatProejct.Chat.domain;

import com.dongjae.nearChatProejct.Common.domain.BaseTimeEntity;
import com.dongjae.nearChatProejct.User.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ChatRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="room_id")
    private Long id;

    @Column(nullable = false,unique = false)
    private String name;

    @Builder
    public ChatRoom(String name) {
        this.name = name;
    }
}
