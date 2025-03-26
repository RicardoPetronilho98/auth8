package com.playground.auth8.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum TokenType {
    ACCESS_TOKEN("urn:ietf:params:oauth:token-type:access_token"),
    REFRESH_TOKEN("urn:ietf:params:oauth:token-type:refresh_token"),
    ID_TOKEN("urn:ietf:params:oauth:token-type:id_token"),
    BEARER("Bearer");

    public final String value;
}
