package com.playground.auth8.util;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Data
@Log4j2
@Configuration
@ConfigurationProperties(prefix = "oauth2")
public class OAuth2Properties {

    private Idp idp;
    private Token token;

    @Data
    public static class Idp {
        private String jwksUri;
    }

    @Data
    public static class Token {
        private String endpoint;
        private AccessToken access;
        private RefreshToken refresh;
        private Claims claims;

        @Data
        public static class AccessToken {
            private long ttlMinutes;

            public Duration getTtl() {
                return Duration.ofMinutes(ttlMinutes);
            }
        }

        @Data
        public static class RefreshToken {
            private long ttlMinutes;
            private boolean rotation;

            public Duration getTtl() {
                return Duration.ofMinutes(ttlMinutes);
            }
        }

        @Data
        public static class Claims {
            private String issuer;
            private String audience;
        }

    }

}