package com.dongjae.nearChatProejct.Common.Error;
import com.dongjae.nearChatProejct.Common.Error.Exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private final LocalDateTime time = LocalDateTime.now();
    private final int status;
    private final String message;
    private final String code;
    private final String error;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build());
    }

}
