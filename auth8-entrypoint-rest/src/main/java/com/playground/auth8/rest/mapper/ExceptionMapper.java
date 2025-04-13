package com.playground.auth8.rest.mapper;

import com.playground.auth8.exception.BaseException;
import com.playground.auth8.rest.exception.ExceptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ExceptionMapper {

    ExceptionDto toExceptionDto(BaseException exception);

}
