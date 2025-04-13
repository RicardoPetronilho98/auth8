package com.playground.auth8.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExceptionDto {

    private String code;
    private String reason;
    private String message;
    private int status;

}
