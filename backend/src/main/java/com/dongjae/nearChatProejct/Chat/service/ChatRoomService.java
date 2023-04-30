package com.dongjae.nearChatProejct.Chat.service;

import com.dongjae.nearChatProejct.Chat.domain.ChatRoom;
import com.dongjae.nearChatProejct.Chat.domain.ChatRoomRepository;
import com.dongjae.nearChatProejct.Chat.exception.ChatRoomCreateException;
import com.dongjae.nearChatProejct.Chat.exception.ChatRoomNotFoundException;
import com.dongjae.nearChatProejct.User.domain.UserLatLotEntity;
import com.dongjae.nearChatProejct.User.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final EntityManager em;

    public ChatRoom findByChatId(Long id) {
        return this.chatRoomRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public void createRoom(ChatRoom chatRoom) {
        try {
            this.chatRoomRepository.save(chatRoom);
        }
        catch (Exception e){
            throw new ChatRoomCreateException();
        }
    }

    public List<ChatRoom> searchChatRoom(UserLatLotEntity dto){
        //radius 는 미터 단
        Double lot = dto.getLot();
        Double lat = dto.getLat();
        int radius = dto.getRadius();

        Query query =  em.createNativeQuery("SELECT b.* FROM Chat_Room AS b where 6371 * acos( cos( radians( :lat ) ) * cos( radians( b.lat) ) * cos( radians( b.lot ) - radians(:lot) ) + sin( radians(:lat) ) * sin( radians(b.lat) ) ) < :radius", ChatRoom.class).setParameter("lat",lat).setParameter("lot",lot).setParameter("radius",radius);

        List resultList = query.getResultList();
        if(resultList.size() == 0 || resultList.size() <0){
            throw new ChatRoomNotFoundException();
        }
        return resultList;
    }

    public void deleteAll(){
        this.chatRoomRepository.deleteAll();
    }
}
