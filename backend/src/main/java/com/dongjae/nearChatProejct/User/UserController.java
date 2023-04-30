package com.dongjae.nearChatProejct.User;


import com.dongjae.nearChatProejct.Common.Security.JwtTokenProvider;
import com.dongjae.nearChatProejct.User.Dto.*;
import com.dongjae.nearChatProejct.User.Service.UserService;
import com.dongjae.nearChatProejct.User.domain.UserEntity;
import com.dongjae.nearChatProejct.User.domain.UserLatLotEntity;
import com.dongjae.nearChatProejct.User.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.h2.engine.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    final private UserService userService;
    final private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/check")
    public ResponseEntity<UserResponseDto> findByDeviceId(@AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok(
                new UserResponseDto(userService.findByDeviceId(user.getDeviceId()),200)
        );
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserSignInResponseDto> signIn(@RequestBody UserSignRequestDto dto) {
        UserEntity userEntity = userService.LoginCheck(dto.getDeviceId(), dto.getName());
        String token = jwtTokenProvider.CreateToken(userEntity);
        return ResponseEntity.ok(
                new UserSignInResponseDto(token,200)
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserSignUpRequestDto dto) {

        userService.CheckDeviceIdAndNickNameOrThr(dto.getDeviceId(), dto.getName());
        userService.CreateUser(dto.ToEntity(dto.getDeviceId()));
        return ResponseEntity.ok(
                new UserResponseDto(userService.findByDeviceId(dto.getDeviceId()),200)
        );
    }
    @PostMapping("/device-check")
    public ResponseEntity<UserResponseDto> deviceCheck(@RequestBody UserDeviceResponseDto dto){
        userService.findByDeviceId(dto.getDeviceId());
        return ResponseEntity.ok(
                new UserResponseDto(userService.findByDeviceId(dto.getDeviceId()),200)
        );
    }

    @PostMapping("/create-coordinate")
    public ResponseEntity<UserResponseDto> CreateCoordinate(@AuthenticationPrincipal UserEntity user, @RequestBody CreateXYRadiusRequestDto dto){
        String deviceId = user.getDeviceId();
        UserEntity userEntity = userService.findByDeviceId(deviceId);
        UserLatLotEntity userLatLotEntity = UserLatLotEntity.builder().userEntity(userEntity).lat(dto.getLat()).lot(dto.getLot()).radius(dto.getRadius()).build();
        if(userService.UserCoordinateNotIn(userEntity)){
            throw new UserNotFoundException();
        }else{
            userService.CreateCoordinate(userLatLotEntity);
        }
        return ResponseEntity.ok(new UserResponseDto(userEntity, 200));
    }
    @PostMapping("/update-coordinate")
    public ResponseEntity<UserResponseDto> UpdateCoordinate(@AuthenticationPrincipal UserEntity user, @RequestBody CreateXYRadiusRequestDto dto){
        String deviceId = user.getDeviceId();
        UserEntity userEntity = userService.findByDeviceId(deviceId);
        userService.UpdateCoordinate(deviceId, dto.getLat(), dto.getLot());
        return ResponseEntity.ok(new UserResponseDto(userEntity, 200));
    }

}
