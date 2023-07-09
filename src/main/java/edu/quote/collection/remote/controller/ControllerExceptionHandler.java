package edu.quote.collection.remote.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import edu.quote.collection.remote.ErrorCode;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException runtimeException) {
        log.error(runtimeException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorCode(runtimeException.getMessage()));
    }

    private String getErrorCode(String exceptionMessage) {
        return Arrays.stream(ErrorCode.values())
                .map(Enum::toString)
                .filter(code -> code.equals(exceptionMessage))
                .findFirst()
                .orElse(ErrorCode.UNKNOWN_ERROR.toString());
    }
}
