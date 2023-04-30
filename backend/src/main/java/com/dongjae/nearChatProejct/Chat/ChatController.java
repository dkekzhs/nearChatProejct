package com.dongjae.nearChatProejct.Chat;

import com.dongjae.nearChatProejct.Chat.domain.ChatRoom;
import com.dongjae.nearChatProejct.Chat.domain.Message;
import com.dongjae.nearChatProejct.Chat.dto.ChatRoomsListResponseDto;
import com.dongjae.nearChatProejct.Chat.dto.RoomRequestDto;
import com.dongjae.nearChatProejct.Chat.dto.RoomResponseDto;
import com.dongjae.nearChatProejct.Chat.service.ChatRoomService;
import com.dongjae.nearChatProejct.Chat.service.MessageService;
import com.dongjae.nearChatProejct.User.Dto.UserResponseDto;
import com.dongjae.nearChatProejct.User.Service.UserService;
import com.dongjae.nearChatProejct.User.domain.UserEntity;
import com.dongjae.nearChatProejct.User.domain.UserLatLotEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ChatController {
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final UserService userService;

    @PostMapping("/create_room")
    public ResponseEntity<RoomResponseDto> createRoom(@AuthenticationPrincipal UserEntity user,
    @RequestBody RoomRequestDto dto) {
        UserEntity id = userService.findById(user.getId());
        chatRoomService.createRoom(ChatRoom.builder().name(dto.getName()).lat(dto.getLat()).lot(dto.getLot()).build());
        return ResponseEntity.ok(
                new RoomResponseDto(dto.getName(),200)
        );
    }
    @MessageMapping("{roomId}")
    @SendTo("/room/{roomId}")
    public Message test(@DestinationVariable Long roomId, Message message){
        ChatRoom byChatId = chatRoomService.findByChatId(roomId);
        Message test = messageService.sendMessage(
                Message.builder().chatRoom(byChatId).message(message.getMessage())
                        .sender(message.getSender()).build()
        );
        return test;
    }

    @GetMapping("/chatRoomList")
    public ResponseEntity<ChatRoomsListResponseDto> selectRoom(@AuthenticationPrincipal UserEntity user){
        UserLatLotEntity userLatLotEntity = user.getUserLatLotEntity();
        List<ChatRoom> chatRooms = chatRoomService.searchChatRoom(userLatLotEntity);
        return ResponseEntity.ok(new ChatRoomsListResponseDto<>(chatRooms, 200));

    }
}
