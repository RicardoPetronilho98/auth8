package com.playground.auth8.nosql.mapper;

import com.playground.auth8.domain.Client;
import com.playground.auth8.nosql.document.ClientDocument;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ClientMapper {

    Client toClient(ClientDocument clientDocument);

}
