package com.dongjae.nearChatProejct.Chat.dto;

public class ChatRoomsListResponseDto<T> {
    private T data;
    private int status;

    public ChatRoomsListResponseDto(T data, int status) {
        this.data = data;
        this.status = status;
    }
}
