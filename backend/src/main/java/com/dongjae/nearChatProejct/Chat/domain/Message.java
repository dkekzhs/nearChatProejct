package com.dongjae.nearChatProejct.Chat.domain;

import com.dongjae.nearChatProejct.Common.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@Getter
@NoArgsConstructor
public class Message extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    private String sender;

    @Builder
    public Message(String message, ChatRoom chatRoom, String sender) {
        this.message = message;
        this.chatRoom = chatRoom;
        this.sender = sender;
    }
}
