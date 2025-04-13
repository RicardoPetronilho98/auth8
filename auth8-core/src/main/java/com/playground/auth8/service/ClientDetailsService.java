package com.playground.auth8.service;

import com.playground.auth8.dataprovider.ClientDataProvider;
import com.playground.auth8.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientDetailsService {

    private final ClientDataProvider dataProvider;

    public boolean validate(String clientId, String clientSecret) {
        Client client = dataProvider.findById(clientId);
        return clientSecret.equals(client.getSecret());
    }

}
