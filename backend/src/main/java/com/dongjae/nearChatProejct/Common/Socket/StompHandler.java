package com.dongjae.nearChatProejct.Common.Socket;

import com.dongjae.nearChatProejct.Common.Error.Exception.SendMessageException;
import com.dongjae.nearChatProejct.Common.Error.Exception.jwt.WrongTokenException;
import com.dongjae.nearChatProejct.Common.Security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {
    private final JwtTokenProvider tokenProvider;
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        switch (accessor.getCommand()) {
            case CONNECT:
                // 유저가 Websocket으로 connect()를 한 뒤 호출됨
                break;
            case DISCONNECT:
                // 유저가 Websocket으로 disconnect() 를 한 뒤 호출됨 or 세션이 끊어졌을 때 발생함(페이지 이동~ 브라우저 닫기 등)
                break;
            default:
                break;
        }
        if(accessor.getCommand() == StompCommand.CONNECT) {
            if(!tokenProvider.validateToken(accessor.getFirstNativeHeader("Authorization")))
                throw new SendMessageException();
        }
        return message;
    }
}
