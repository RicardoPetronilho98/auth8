package com.playground.auth8.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientDetailsService {

    public boolean validate(String clientId, String clientSecret) {
        log.info("\n\nclient ID = {} \nclient secret = {}\n", clientId, clientSecret);
        return true; // TODO
    }

}
