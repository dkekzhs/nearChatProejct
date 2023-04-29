package com.dongjae.nearChatProejct.UserTest;


import com.dongjae.nearChatProejct.Chat.domain.ChatRoom;
import com.dongjae.nearChatProejct.Chat.service.ChatRoomService;
import com.dongjae.nearChatProejct.User.Service.UserService;
import com.dongjae.nearChatProejct.User.domain.UserEntity;
import com.dongjae.nearChatProejct.User.domain.UserLatLotEntity;
import com.dongjae.nearChatProejct.User.domain.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ChatRoomTest {

    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private UserService userService;
    @BeforeEach
    public void 이전지우기(){
        chatRoomService.deleteAll();
    }

    @Test
    public void 채팅룸검색테스트(){
        ChatRoom 채팅방1번 = ChatRoom.builder().name("채팅방1번").lat(12.0).lot(13.0).build();
        ChatRoom 채팅방2번 = ChatRoom.builder().name("채팅방2번").lat(1.0).lot(21.0).build();
        ChatRoom 채팅방3번 = ChatRoom.builder().name("채팅방3번").lat(1.0).lot(341.0).build();
        UserEntity userEntity = UserEntity.builder().deviceId("123").name("dads").role(UserRole.USER).build();
        UserLatLotEntity userLatLotEntity = UserLatLotEntity.builder().lot(1.0).lat(1.0).radius(3).build();

        userService.CreateUser(userEntity);
        userService.CreateCoordinate(userEntity.getDeviceId(),userLatLotEntity);
        chatRoomService.createRoom(채팅방1번);
        chatRoomService.createRoom(채팅방2번);
        chatRoomService.createRoom(채팅방3번);


        List<ChatRoom> chatRooms = chatRoomService.searchChatRoom(userLatLotEntity);
        for (ChatRoom c : chatRooms) {
            System.out.println("c.getName() = " + c.getName());
        }

    }
}
