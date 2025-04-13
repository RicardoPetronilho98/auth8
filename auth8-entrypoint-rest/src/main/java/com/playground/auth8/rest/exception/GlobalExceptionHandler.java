package com.playground.auth8.rest.exception;

import com.playground.auth8.exception.BaseException;
import com.playground.auth8.exception.ErrorCode;
import com.playground.auth8.rest.mapper.ExceptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ExceptionMapper mapper;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDto> handleGenericException(Exception e, WebRequest request) {
        BaseException exception = new BaseException(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        return handleBaseException(exception, request);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionDto> handleBaseException(BaseException e, WebRequest request) {
        ExceptionDto dto = mapper.toExceptionDto(e);
        return ResponseEntity.status(e.getStatus()).body(dto);
    }

}
