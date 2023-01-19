package com.dongjae.nearChatProejct.UserTest;

import com.dongjae.nearChatProejct.User.Service.UserService;
import com.dongjae.nearChatProejct.User.domain.UserEntity;
import com.dongjae.nearChatProejct.User.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class User {

    @Autowired
    private UserService userService;

    @BeforeEach
    public void 이전지우기(){
        userService.deleteAll();
    }
    @Test
    public void 유저생성테스트(){
        UserEntity build = UserEntity.builder().deviceId("").name("dads").build();
        userService.CreateUser(build);

    }

}
