package com.playground.auth8.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final String code;
    private final String reason;
    private final String message;
    private final int status;

    public BaseException(ErrorCode code, String reason) {
        super(reason);
        this.code = code.getCode();
        this.reason = reason;
        this.message = null;
        this.status = code.getStatus().value();
    }

    public BaseException(ErrorCode code, String reason, String message) {
        super(reason);
        this.code = code.getCode();
        this.reason = reason;
        this.message = message;
        this.status = code.getStatus().value();
    }

}
