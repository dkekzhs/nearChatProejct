package com.dongjae.nearChatProejct.User.Service;

import com.dongjae.nearChatProejct.User.domain.UserEntity;
import com.dongjae.nearChatProejct.User.domain.UserRepository;
import com.dongjae.nearChatProejct.User.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserEntity CreateUser(UserEntity user){
        this.userRepository.save(user);
        return user;
    }
    public UserEntity findById(Long id){
        return this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public UserEntity CheckDeviceIdAndNickNameOrThr(String deviceId, String name) {
        this.userRepository.findByDeviceId(deviceId)
                .ifPresent(e ->{
                    throw new UserAlreadyDeviceId();
                });
        this.userRepository.findByName(name)
                .ifPresent(e ->{
                    throw new UserAlreadyNameId();
                });
        if (!StringUtils.hasText(name)){
            throw new UserBlankNickName();
        }

        return UserEntity.builder().deviceId(deviceId).name(name).build();
    }

    public UserEntity LoginCheck(String deviceId, String name) {
        UserEntity id = this.userRepository.findByDeviceId(deviceId)
                .orElseThrow(UserNotFoundException::new);

        if (id.getName().equals(name)) {
            return id;
        }
        throw new UserNotMatchName();
    }
    public void deleteAll(){
        this.userRepository.deleteAll();
    }

    public UserEntity findByDeviceId(String deviceId){
        Optional<UserEntity> user = this.userRepository.findByDeviceId(deviceId);
        if(user.isPresent()){
            return user.get();
        }
        else{
            throw new UserNotFoundException();
        }
    }

}
