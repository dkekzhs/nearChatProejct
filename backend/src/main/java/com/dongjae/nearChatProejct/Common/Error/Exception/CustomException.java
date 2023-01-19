package com.dongjae.nearChatProejct.Common.Error.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode = ErrorCode.findByClass(this.getClass());

    private HttpStatus httpStatus;
    private String Message;

    public CustomException(){
        this.httpStatus = errorCode.getHttpStatus();
        this.Message = errorCode.getMessage();
    }
}
