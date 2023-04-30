package com.dongjae.nearChatProejct.UserTest;

import com.dongjae.nearChatProejct.User.Service.UserService;
import com.dongjae.nearChatProejct.User.domain.UserEntity;
import com.dongjae.nearChatProejct.User.domain.UserLatLotEntity;
import com.dongjae.nearChatProejct.User.domain.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;


    @BeforeEach
    public void 이전지우기(){
        userService.deleteAll();
    }
    @Test
    public void 유저생성테스트(){

        UserEntity build = UserEntity.builder().deviceId("123").name("dads").role(UserRole.USER).build();
        UserLatLotEntity build1 = UserLatLotEntity.builder().lot(123.312).lat(123.1).radius(3).build();
        Double lat =123.3;
        Double lot = 1234.3;
        userService.CreateUser(build);
        userService.CreateCoordinate(build1);
        userService.UpdateCoordinate("123",lat,lot);



    }

}
