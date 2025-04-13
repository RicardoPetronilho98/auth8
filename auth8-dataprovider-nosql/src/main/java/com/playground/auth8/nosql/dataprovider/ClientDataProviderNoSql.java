package com.playground.auth8.nosql.dataprovider;

import com.playground.auth8.dataprovider.ClientDataProvider;
import com.playground.auth8.domain.Client;
import com.playground.auth8.exception.BaseException;
import com.playground.auth8.exception.ErrorCode;
import com.playground.auth8.nosql.document.ClientDocument;
import com.playground.auth8.nosql.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ClientDataProviderNoSql implements ClientDataProvider {

    private final MongoTemplate mongo;
    private final ClientMapper mapper;

    @Override
    public Client findById(String id) {
        Query query = new Query().addCriteria(
                Criteria.where("id").is(id)
        );
        return mongo.find(query, ClientDocument.class)
                .stream()
                .map(mapper::toClient)
                .findFirst()
                .orElseThrow(() ->
                        new BaseException(
                                ErrorCode.NOT_FOUND,
                                String.format("Client %s not found", id)
                        )
                );
    }

}
