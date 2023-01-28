package com.dongjae.nearChatProejct.Chat.service;

import com.dongjae.nearChatProejct.Chat.domain.Message;
import com.dongjae.nearChatProejct.Chat.domain.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message sendMessage(Message message) {
        this.messageRepository.save(message);

        return message;
    }
}
