package com.dongjae.nearChatProejct.Common.Error.Exception;

import com.dongjae.nearChatProejct.Chat.exception.ChatRoomNotFoundException;
import com.dongjae.nearChatProejct.Common.Error.Exception.jwt.ExpiredTokenException;
import com.dongjae.nearChatProejct.Common.Error.Exception.jwt.InvalidTokenException;
import com.dongjae.nearChatProejct.Common.Error.Exception.jwt.WrongTokenException;
import com.dongjae.nearChatProejct.User.exception.*;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Getter
public enum ErrorCode {
    /* 400 에러 */
    USER_NOT_FOUND(BAD_REQUEST, "사용자를 찾지 못했습니다.", UserNotFoundException.class),
    USER_DEVICEID_ALREADY(BAD_REQUEST,"이미 존재하는 디바이스 아이디입니다.", UserAlreadyDeviceId.class),
    USER_BLANK_NICKNAME(BAD_REQUEST,"아이디를 입력하지 않았습니다.", UserBlankNickName.class),
    USER_NAMEID_ALREDY(BAD_REQUEST, "이미 존재하는 이름입니다.", UserAlreadyNameId.class),
    USER_NOTMATCH_NAME(BAD_REQUEST, "디바이스 아이디와 이름이 일치하지 않습니다.", UserNotMatchName.class),
    EXPIRED_TOKEN(BAD_REQUEST, "만료된 토큰입니다", ExpiredTokenException.class),
    INVALID_TOKEN(BAD_REQUEST, "옳바르지 않은 형식의 토큰입니다", InvalidTokenException.class),
    WRONG_TOKEN(BAD_REQUEST, "잘못된 토큰입니다", WrongTokenException.class),
    SEND_MESSAGE_ERROR(BAD_REQUEST,"메세지 전송에 실패했습니다.",SendMessageException.class),

    /*403 에러 */
    UNAUTHORIZED_REQUEST(FORBIDDEN,"허가되지 않은 요청입니다.", UnauthorizedRequestException.class),

    /*404 에러 */
    CLASS_NOT_FOUND(NOT_FOUND, "에러 클래스를 찾을 수 없습니다.", NotFoundClassException.class),
    CHAT_ROOM_NOT_FOUND(NOT_FOUND, "채팅방이 존재하지 않습니다.", ChatRoomNotFoundException.class);
    private final HttpStatus httpStatus;
    private final String message;
    private final Class<? extends Exception> klass;


    ErrorCode(HttpStatus httpStatus, String message, Class<? extends Exception> klass) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.klass = klass;
    }

    public static ErrorCode findByClass(Class<? extends Exception> klass) {
        return Arrays.stream(ErrorCode.values())
                .filter(code -> code.klass.equals(
                        klass
                ))
                .findAny()
                .orElseThrow(NotFoundClassException::new);
    }

    }

