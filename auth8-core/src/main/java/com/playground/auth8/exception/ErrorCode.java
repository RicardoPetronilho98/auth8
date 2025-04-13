package com.playground.auth8.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
@ToString
public enum ErrorCode {

    AUTHENTICATION_FAILED("Auth8-E-001", HttpStatus.UNAUTHORIZED),
    AUTHORIZATION_FAILED("Auth8-E-002", HttpStatus.FORBIDDEN),
    BAD_REQUEST("Auth8-E-003", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("Auth8-E-004", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("Auth8-E-005", HttpStatus.NOT_FOUND);

    private final String code;
    private final HttpStatus status;

}
