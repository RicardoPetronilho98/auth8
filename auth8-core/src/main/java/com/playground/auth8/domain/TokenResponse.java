package com.playground.auth8.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TokenResponse {

    @JsonProperty("issued_token_type")
    private String issuedTokenType;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private long expiresIn;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonIgnore
    private List<String> scope;

    @JsonProperty("scope")
    public String getScopeAsString() {
        return scope == null
                ? null
                : String.join(" ", scope);
    }

    @JsonProperty("aud")
    private List<String> audience;

}
