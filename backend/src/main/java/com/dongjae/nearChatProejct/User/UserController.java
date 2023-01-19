package com.dongjae.nearChatProejct.User;


import com.dongjae.nearChatProejct.Common.Security.JwtTokenProvider;
import com.dongjae.nearChatProejct.User.Dto.UserSignRequestDto;
import com.dongjae.nearChatProejct.User.Dto.UserResponseDto;
import com.dongjae.nearChatProejct.User.Dto.UserSignInResponseDto;
import com.dongjae.nearChatProejct.User.Dto.UserSignUpRequestDto;
import com.dongjae.nearChatProejct.User.Service.UserService;
import com.dongjae.nearChatProejct.User.domain.UserEntity;
import lombok.RequiredArgsConstructor;
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
                new UserResponseDto(userService.findByDeviceId(user.getDeviceId()))
        );
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserSignInResponseDto> signIn(@RequestBody UserSignRequestDto dto) {
        UserEntity userEntity = userService.LoginCheck(dto.getDeviceId(), dto.getName());
        String token = jwtTokenProvider.CreateToken(userEntity);
        return ResponseEntity.ok(
                new UserSignInResponseDto(token)
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserSignUpRequestDto dto) {

        userService.CheckDeviceIdAndNickNameOrThr(dto.getDeviceId(), dto.getName());
        userService.CreateUser(dto.ToEntity(dto.getDeviceId()));
        return ResponseEntity.ok(
                new UserResponseDto(userService.findByDeviceId(dto.getDeviceId()))
        );
    }

}
